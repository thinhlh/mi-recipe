package com.thinhlh.mi_recipe.view.create_recipe;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.domain.repository.recipe.Direction;
import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.mi_recipe.App;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.activity.RequestPermissionCallback;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.base.dialog.AlertDialogOnClickListener;
import com.thinhlh.mi_recipe.base.dialog.AppAlertDialog;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentCreateRecipeBinding;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateDirectionAdapter;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateIngredient;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateIngredientAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CreateRecipeFragment extends BaseFragment<FragmentCreateRecipeBinding, CreateRecipeVM> implements CreateRecipeUV {

    private CreateIngredientAdapter createIngredientAdapter;
    private CreateDirectionAdapter createDirectionAdapter;

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
    protected Integer layoutRes() {
        return R.layout.fragment_create_recipe;
    }

    @Override
    protected Class<CreateRecipeVM> viewModelClass() {
        return CreateRecipeVM.class;
    }

    @Override
    protected void initViewModel(CreateRecipeVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        initAdapters();
    }

    @Override
    protected void initData() {
        viewModel.getIngredients();
    }

    @Override
    protected void initAction() {

    }

    private void initAdapters() {
        createIngredientAdapter = new CreateIngredientAdapter((item, adapterPosition) -> {

        }, itemAddPosition -> {
            var currentList = viewModel.createIngredientList.getValue();
            if (currentList != null) {
                currentList.add(new CreateIngredient());
            }
        }, itemRemovePosition -> {
            var currentList = viewModel.createIngredientList.getValue();
            if (currentList != null && !currentList.isEmpty()) {
                currentList.remove((int) itemRemovePosition);
            }
        });

        binding.ingredientRv.setAdapter(createIngredientAdapter);
        binding.ingredientRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_M));

        createIngredientAdapter.submitList(viewModel.createIngredientList.getValue());


        createDirectionAdapter = new CreateDirectionAdapter((item, adapterPosition) -> {

        }, itemAddPosition -> {
            var currentList = viewModel.createDirectionList.getValue();
            if (currentList != null) {
                currentList.add("");
            }
        }, itemRemovePosition -> {
            var currentList = viewModel.createDirectionList.getValue();
            if (currentList != null && !currentList.isEmpty()) {
                currentList.remove((int) itemRemovePosition);
            }
        });

        binding.directionRv.setAdapter(createDirectionAdapter);
        binding.directionRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_M));

        createDirectionAdapter.submitList(viewModel.createDirectionList.getValue());

    }

    @Override
    public void updateIngredients(List<Ingredient> ingredients) {
        createIngredientAdapter.addIngredients(ingredients);
    }

    @Override
    public String getRecipeTitle() {
        var editable = binding.title.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("Title cannot be empty");
            viewModel.formCorrect.setValue(false);
            return null;
        } else {
            viewModel.formCorrect.setValue(true);
            return editable.toString();
        }
    }

    @Override
    public String getRecipeDescription() {
        var editable = binding.description.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("Description cannot be empty");
            viewModel.formCorrect.setValue(false);
            return null;
        } else {
            viewModel.formCorrect.setValue(true);
            return editable.toString();
        }
    }

    @Override
    public Integer getRecipeCalories() {
        var editable = binding.calories.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("Calories cannot be empty");
            viewModel.formCorrect.setValue(false);
            return null;
        } else {
            var stringValue = editable.toString();
            viewModel.formCorrect.setValue(true);
            return Integer.parseInt(stringValue);
        }
    }

    @Override
    public Integer getRecipeTakenTime() {
        var editable = binding.takenTime.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("Taken time cannot be empty");
            viewModel.formCorrect.setValue(false);
            return null;
        } else {
            var stringValue = editable.toString();
            viewModel.formCorrect.setValue(true);
            return Integer.parseInt(stringValue);
        }
    }

    @Override
    public Integer getPeople() {
        var editable = binding.people.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("For number of people cannot be empty");
            viewModel.formCorrect.setValue(false);
            return null;
        } else {
            var stringValue = editable.toString();
            viewModel.formCorrect.setValue(true);
            return Integer.parseInt(stringValue);
        }
    }

    @Override
    public List<Ingredient> getIngredients() {
        return new ArrayList<>();
    }

    @Override
    public List<Direction> getDirections() {
        return createDirectionAdapter.getDirections();
    }

    @Override
    public void openImagePicker() {
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
                true
        );
    }

    private File createImageFile() throws IOException {
        if (getActivity() != null) {
            var storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            return File.createTempFile("temp_image", ".jpg", storageDir);
        }
        return null;

    }
}
