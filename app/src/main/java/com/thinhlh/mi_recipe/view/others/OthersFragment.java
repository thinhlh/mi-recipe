package com.thinhlh.mi_recipe.view.others;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.domain.repository.recipe.CreateRecipeRequest;
import com.thinhlh.mi_recipe.App;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.base.dialog.AlertDialogOnClickListener;
import com.thinhlh.mi_recipe.base.dialog.AppAlertDialog;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.data.FirebaseStorageHelper;
import com.thinhlh.mi_recipe.databinding.FragmentOthersBinding;
import com.thinhlh.mi_recipe.view.category.CategoryFragment;
import com.thinhlh.mi_recipe.view.change_password.ChangePasswordFragment;
import com.thinhlh.mi_recipe.view.landing.LandingFragment;
import com.thinhlh.mi_recipe.view.others.adapters.Setting;
import com.thinhlh.mi_recipe.view.others.adapters.SettingAdapter;
import com.thinhlh.mi_recipe.view.others.adapters.SettingGroup;
import com.thinhlh.mi_recipe.view.others.adapters.SettingGroupAdapter;
import com.thinhlh.mi_recipe.view.saved_recipe.SavedRecipeFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OthersFragment extends BaseFragment<FragmentOthersBinding, OthersVM> implements OthersUV {
    private SettingGroupAdapter settingGroupAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_others;
    }

    @Override
    protected Class<OthersVM> viewModelClass() {
        return OthersVM.class;
    }

    @Override
    protected void initViewModel(OthersVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        settingGroupAdapter = new SettingGroupAdapter((item, adapterPosition) -> {

        });

        binding.recyclerView.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_L));
        binding.recyclerView.setAdapter(settingGroupAdapter);
    }

    @Override
    protected void initData() {
        settingGroupAdapter.submitList(new ArrayList<>() {{
            add(new SettingGroup("Account", new ArrayList<>() {{
//                add(new Setting("Edit profile", false, () -> {
//
//                }));
                add(new Setting("Change password", false, () -> {
                    getNavigator().goTo(new ChangePasswordFragment());
                }));
            }}));

            add(new SettingGroup("Preferences", new ArrayList<>() {{
                add(new Setting("Language", false, () -> {

                }));
                add(new Setting("Saved Recipes", false, () -> {
                    getNavigator().goTo(new SavedRecipeFragment());
                }));
                add(new Setting("Licenses", false, () -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/thinhlh/mi-recipe"));
                    startActivity(browserIntent);
                }));
            }}));
        }});

        viewModel.getUserDetail();
    }

    private Uri tempImageUri = null;
    private String tempImageAbsolutePath = null;

    private final ActivityResultLauncher<Uri> takePictureActivityRegisterResult = registerForActivityResult(new ActivityResultContracts.TakePicture(), success -> {
        if (success) {
            viewModel.selectedImageUri.postValue(tempImageUri);
        }
    });
    private final ActivityResultLauncher<String> pickPictureActivityRegisterResult = registerForActivityResult(new ActivityResultContracts.GetContent(),
            result -> viewModel.selectedImageUri.postValue(result));

    @Override
    public void showAvatarChosenDialog() {
        AppAlertDialog.get().show(
                "Select your image source",
                "Recipe Thumbnail",
                "File",
                "Camera",
                new AlertDialogOnClickListener() {
                    @Override
                    public void onPositiveClick() {
                        pickPictureActivityRegisterResult.launch("image/*");
                    }

                    @Override
                    public void onNegativeClick() {
                        try {
                            var tempFile = createImageFile();
                            if (tempFile != null) {
                                tempImageAbsolutePath = tempFile.getAbsolutePath();
                                tempImageUri = FileProvider.getUriForFile(fragmentContext, App.PACKAGE_NAME + ".provider", tempFile);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        takePictureActivityRegisterResult.launch(tempImageUri);
                    }
                },
                false,
                false
        );
    }

    public void uploadThumbnail(Uri uri) {
        showLoading(true);
        FirebaseStorageHelper
                .upload("/user/" + viewModel.userDetail.getValue().getId() + ".jpg", uri)
                .addOnFailureListener(e -> {
                    showLoading(false);
                    showError(e.getMessage(), "Upload error", null, null, null, null);
                })
                .addOnSuccessListener(taskSnapshot -> {
                    FirebaseStorageHelper
                            .getDownloadLink(taskSnapshot.getMetadata().getPath())
                            .addOnCompleteListener(task -> {
                                showLoading(false);
                            })
                            .addOnSuccessListener(firebaseUri -> {
                                showLoading(false);
                                var userDetail = viewModel.userDetail.getValue();
                                userDetail.setAvatar(firebaseUri.toString());
                                viewModel.userDetail.setValue(userDetail);
                            }).addOnFailureListener(e -> {
                                showLoading(false);
                                showError(e.getMessage(), "Get download link error error", null, null, null, null);
                            });
                });
    }

    private File createImageFile() throws IOException {
        if (getActivity() != null) {
            var storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            return File.createTempFile("temp_image", ".jpg", storageDir);
        }
        return null;
    }

    @Override
    protected void initAction() {
        viewModel.selectedImageUri.observe(this, this::uploadThumbnail);
    }

    @Override
    public void goToLandingPage() {
        getNavigator().setRootFragment(new LandingFragment());
        getNavigator().goToRoot();
        getNavigator().clearHistory();
    }
}
