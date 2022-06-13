package com.thinhlh.domain.repository.recipe;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;

import java.util.List;

public class RecipeRepo extends BaseRepo {
    public void getRecipes(GetRecipesType type, BaseRepoCallback<BaseResponse<List<Recipe>>> callback) {
        recipeService.getRecipes(type).enqueue(getApiCallback(callback));
    }
}
