package com.thinhlh.domain.repository.ingredient;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.domain.repository.recipe.Recipe;

import java.util.List;

public class IngredientRepo extends BaseRepo {
    public void getIngredients(BaseRepoCallback<BaseResponse<List<Ingredient>>> callback) {
        ingredientService.getAllIngredients().enqueue(getApiCallback(callback));
    }
}
