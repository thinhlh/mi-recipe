package com.thinhlh.mi_recipe.view.explorer.tabs.top_ratings;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.recipe.GetRecipesType;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

import java.util.List;

public class TopChartVM extends BaseRepoViewModel<RecipeRepo, TopChartUV> {

    public void getTopChartRecipes() {
        getRepo().getRecipes(GetRecipesType.TOP_RATINGS, new BaseRepoCallback<BaseResponse<List<Recipe>>>() {
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

    @Override
    protected RecipeRepo createRepo() {
        return new RecipeRepo();
    }
}
