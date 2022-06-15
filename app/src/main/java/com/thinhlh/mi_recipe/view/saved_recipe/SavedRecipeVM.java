package com.thinhlh.mi_recipe.view.saved_recipe;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.recipe.GetRecipesType;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseViewModel;

import java.util.List;

public class SavedRecipeVM extends BaseRepoViewModel<RecipeRepo, SavedRecipeUV> {

    public void getSavedRecipe() {
        getRepo().getRecipes(GetRecipesType.SAVED, new BaseRepoCallback<>() {
            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Recipe>> data) {
                uiCallback.updateRecipes(data.getData());
            }
        });
    }

    public void onBackPressedClick() {
        uiCallback.onFragmentBackPressed();
    }

    @Override
    protected RecipeRepo createRepo() {
        return new RecipeRepo();
    }
}
