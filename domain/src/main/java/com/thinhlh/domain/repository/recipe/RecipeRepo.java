package com.thinhlh.domain.repository.recipe;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.category.Category;

import java.util.List;

public class RecipeRepo extends BaseRepo {
    public void getRecipes(GetRecipesType type, BaseRepoCallback<BaseResponse<List<Recipe>>> callback, String categoryId) {
        recipeService.getRecipes(type, categoryId).enqueue(getApiCallback(callback));
    }

    public void getRecipes(GetRecipesType type, BaseRepoCallback<BaseResponse<List<Recipe>>> callback) {
        recipeService.getRecipes(type, null).enqueue(getApiCallback(callback));
    }

    public void createRecipe(CreateRecipeRequest request, BaseRepoCallback<BaseResponse<Recipe>> callback) {
        recipeService.createRecipe(request).enqueue(getApiCallback(callback));
    }

    public void toggleSaveRecipe(String recipeId, BaseRepoCallback<BaseResponse<Boolean>> callback) {
        recipeService.toggleSaveRecipe(recipeId).enqueue(getApiCallback(callback));
    }
}
