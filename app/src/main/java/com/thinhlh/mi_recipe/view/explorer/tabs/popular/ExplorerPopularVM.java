package com.thinhlh.mi_recipe.view.explorer.tabs.popular;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.recipe.GetRecipesType;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;
import com.thinhlh.mi_recipe.view.explorer.ExplorerVM;

import java.util.List;

public class ExplorerPopularVM extends BaseRepoViewModel<RecipeRepo, ExplorerPopularUV> {

    void getAllPopularRecipes() {
        getRepo().getRecipes(GetRecipesType.POPULAR, new BaseRepoCallback<BaseResponse<List<Recipe>>>() {
            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Recipe>> data) {
                uiCallback.updatePopularRecipes(data.getData());
            }
        });
    }

    @Override
    protected RecipeRepo createRepo() {
        return new RecipeRepo();
    }
}
