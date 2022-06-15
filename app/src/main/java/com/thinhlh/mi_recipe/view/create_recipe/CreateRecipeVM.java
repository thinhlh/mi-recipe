package com.thinhlh.mi_recipe.view.create_recipe;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.category.CategoryRepo;
import com.thinhlh.domain.repository.ingredient.IngredientRepo;
import com.thinhlh.domain.repository.recipe.CreateRecipeRequest;
import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;
import com.thinhlh.mi_recipe.data.FirebaseStorageHelper;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateIngredient;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeVM extends BaseRepoViewModel<IngredientRepo, CreateRecipeUV> {

    public MutableLiveData<Boolean> formCorrect = new MutableLiveData<>(false);

    public final MutableLiveData<List<CreateIngredient>> createIngredientList = new MutableLiveData<>(new ArrayList<>() {{
        add(new CreateIngredient(null, "", 0, ""));
    }});

    public final MutableLiveData<List<String>> createDirectionList = new MutableLiveData<>(new ArrayList<>() {{
        add("");
    }});

    public final MutableLiveData<List<Category>> createCategoryList = new MutableLiveData<>(new ArrayList<>() {{
        add(new Category(null, "", null));
    }});

    public void validateRecipe() {

        try {
            CreateRecipeRequest request = new CreateRecipeRequest
                    .Builder()
                    .title(uiCallback.getRecipeTitle())
                    .description(uiCallback.getRecipeDescription())
                    .calories(uiCallback.getRecipeCalories())
                    .people(uiCallback.getPeople())
                    .takenTime(uiCallback.getRecipeTakenTime())
                    .categories(uiCallback.getCategories())
                    .ingredients(uiCallback.getIngredients())
                    .directions(uiCallback.getDirections())
                    .build();
            uiCallback.uploadThumbnail(request);
        } catch (Exception e) {
            showErrorMessage("Invalid form.");
        }
    }

    public void createRecipe(CreateRecipeRequest request) {
        new RecipeRepo().createRecipe(request, data -> {
            onBackPressedClick(data.getData());
        });
    }

    public void fetchData() {
        getIngredients();
        getCategories();
    }

    private void getIngredients() {
        getRepo().getIngredients(data -> {
            uiCallback.updateIngredients(data.getData());
        });
    }

    private void getCategories() {
        new CategoryRepo().getCategories(data -> {
            uiCallback.updateCategories(data.getData());
        });
    }


    public final MutableLiveData<Uri> selectedImageUri = new MutableLiveData<>();

    public void openImagePicker() {
        uiCallback.openImagePicker();
    }

    public void clearImage() {
        selectedImageUri.postValue(null);
    }

    public void onBackPressedClick(Recipe recipe) {
        uiCallback.onFragmentBackPressed(recipe);
    }

    public void onBackPressedClick() {
        uiCallback.onFragmentBackPressed(null);
    }

    @Override
    protected IngredientRepo createRepo() {
        return new IngredientRepo();
    }
}
