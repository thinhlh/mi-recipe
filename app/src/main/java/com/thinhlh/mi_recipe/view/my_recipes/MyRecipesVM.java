package com.thinhlh.mi_recipe.view.my_recipes;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.recipe.GetRecipesType;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

import java.util.List;

public class MyRecipesVM extends BaseRepoViewModel<RecipeRepo, MyRecipesUV> {

    public void getMyRecipes() {
        getRepo().getRecipes(GetRecipesType.ME, new BaseRepoCallback<BaseResponse<List<Recipe>>>() {
            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Recipe>> data) {
                uiCallback.updateMyRecipes(data.getData());
            }
        });
    }

    public void createRecipe() {
        uiCallback.createRecipe();
    }

    @Override
    protected RecipeRepo createRepo() {
        return new RecipeRepo();
    }
}
