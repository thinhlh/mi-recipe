package com.thinhlh.mi_recipe.view.create_recipe;

import android.net.Uri;
import android.os.Environment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.recipe.CreateRecipeRequest;
import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.App;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.dialog.AlertDialogOnClickListener;
import com.thinhlh.mi_recipe.base.dialog.AppAlertDialog;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.data.FirebaseStorageHelper;
import com.thinhlh.mi_recipe.databinding.FragmentCreateRecipeBinding;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateDirectionAdapter;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateIngredient;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateIngredientAdapter;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateCategoryAdapter;
import com.thinhlh.mi_recipe.view.home.HomeFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateRecipeFragment extends BaseFragment<FragmentCreateRecipeBinding, CreateRecipeVM> implements CreateRecipeUV {

    private CreateIngredientAdapter createIngredientAdapter;
    private CreateDirectionAdapter createDirectionAdapter;
    private CreateCategoryAdapter createCategoryAdapter;

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
        viewModel.fetchData();
    }

    @Override
    protected void initAction() {

    }

    private void initAdapters() {
        initIngredientAdapter();
        initCategoryAdapter();
        initDirectionAdapter();
    }

    private void initCategoryAdapter() {
        createCategoryAdapter = new CreateCategoryAdapter((item, adapterPosition) -> {
        }, itemAddPosition -> {
            var currentList = viewModel.createCategoryList.getValue();
            if (currentList != null) {
                currentList.add(new Category());
            }
        }, itemRemovePosition -> {
            var currentList = viewModel.createCategoryList.getValue();
            if (currentList != null && !currentList.isEmpty()) {
                currentList.remove((int) itemRemovePosition);
            }
        });

        binding.categoryRv.setAdapter(createCategoryAdapter);
        binding.categoryRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_M));

        createCategoryAdapter.submitList(viewModel.createCategoryList.getValue());
    }

    private void initIngredientAdapter() {
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
    }

    private void initDirectionAdapter() {
        createDirectionAdapter = new CreateDirectionAdapter((item, adapterPosition) -> {

        }, itemAddPosition -> {
            var currentList = viewModel.createDirectionList.getValue();
            if (currentList != null) {
                currentList.add("");
            }

            createDirectionAdapter.submitList(currentList);

        }, itemRemovePosition -> {
            var currentList = viewModel.createDirectionList.getValue();
            if (currentList != null && !currentList.isEmpty()) {
                currentList.remove((int) itemRemovePosition);
            }

            createDirectionAdapter.submitList(currentList);
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
    public void updateCategories(List<Category> categories) {
        createCategoryAdapter.addCategories(categories);
    }

    @Override
    public String getRecipeTitle() throws Exception {
        var editable = binding.title.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("Title cannot be empty");
            viewModel.formCorrect.setValue(false);
            throw new Exception();
        } else {
            viewModel.formCorrect.setValue(true);
            return editable.toString();
        }
    }

    @Override
    public String getRecipeDescription() throws Exception {
        var editable = binding.description.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("Description cannot be empty");
            viewModel.formCorrect.setValue(false);
            throw new Exception();
        } else {
            viewModel.formCorrect.setValue(true);
            return editable.toString();
        }
    }

    @Override
    public Integer getRecipeCalories() throws Exception {
        var editable = binding.calories.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("Calories cannot be empty");
            viewModel.formCorrect.setValue(false);
            throw new Exception();
        } else {
            var stringValue = editable.toString();
            viewModel.formCorrect.setValue(true);
            return Integer.parseInt(stringValue);
        }
    }

    @Override
    public Integer getRecipeTakenTime() throws Exception {
        var editable = binding.takenTime.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("Taken time cannot be empty");
            viewModel.formCorrect.setValue(false);
            throw new Exception();
        } else {
            var stringValue = editable.toString();
            viewModel.formCorrect.setValue(true);
            return Integer.parseInt(stringValue);
        }
    }

    @Override
    public Integer getPeople() throws Exception {
        var editable = binding.people.getText();

        if (editable == null || editable.toString().isEmpty()) {
            binding.title.setError("For number of people cannot be empty");
            viewModel.formCorrect.setValue(false);
            throw new Exception();
        } else {
            var stringValue = editable.toString();
            viewModel.formCorrect.setValue(true);
            return Integer.parseInt(stringValue);
        }
    }

    @Override
    public List<Ingredient> getIngredients() {
        return createIngredientAdapter
                .getCurrentList()
                .stream()
                .map((it) -> new Ingredient(
                        it.getId(),
                        it.getTitle(),
                        it.getQuantity(), it.getUnit())
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getCategories() {
        return createCategoryAdapter.getCurrentList();
    }

    @Override
    public List<String> getDirections() {
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
                false
        );
    }

    @Override
    public void uploadThumbnail(CreateRecipeRequest request) {
        showLoading(true);
        FirebaseStorageHelper
                .upload("/recipes/" + request.getTitle() + ".jpg", viewModel.selectedImageUri.getValue())
                .addOnFailureListener(e -> {
                    showLoading(false);
                    showError(e.getMessage(), "Upload error", null, null, null, null);
                })
                .addOnSuccessListener(taskSnapshot -> {
                    FirebaseStorageHelper
                            .getDownloadLink(taskSnapshot.getMetadata().getPath())
                            .addOnSuccessListener(uri -> {
                                request.setThumbnail(uri.toString());
                                viewModel.createRecipe(request);
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
    public void onFragmentBackPressed(Recipe recipe) {
        showLoading(false);
        onFragmentBackPressed();
        if (recipe != null) {
            try {
                ((HomeFragment) getNavigator().getActiveFragment()).getMyRecipeFragment().getNewRecipes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
