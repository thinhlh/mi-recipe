package com.thinhlh.mi_recipe.view.explorer.tabs.all_recipes;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.recipe.GetRecipesType;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllRecipesVM extends BaseRepoViewModel<RecipeRepo, AllRecipesUV> {

    List<Recipe> recipes = new ArrayList<>();

    public void getAllRecipes() {
        getRepo().getRecipes(GetRecipesType.ALL, new BaseRepoCallback<>() {

            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Recipe>> data) {
                recipes = data.getData();
                uiCallback.updateData(data.getData());
            }
        });
    }

    @Override
    protected RecipeRepo createRepo() {
        return new RecipeRepo();
    }
}
