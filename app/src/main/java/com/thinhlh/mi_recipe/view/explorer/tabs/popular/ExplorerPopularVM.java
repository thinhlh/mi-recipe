package com.thinhlh.mi_recipe.view.explorer.tabs.popular;

import androidx.lifecycle.MutableLiveData;

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

    public MutableLiveData<Recipe> firstRecipe = new MutableLiveData<>();

    void getAllPopularRecipes() {
        getRepo().getRecipes(GetRecipesType.POPULAR, new BaseRepoCallback<>() {
            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Recipe>> data) {
                firstRecipe.setValue(data.getData().stream().findFirst().orElse(null));
                uiCallback.updatePopularRecipes(data.getData().subList(1, data.getData().size()));
            }
        });
    }

    @Override
    protected RecipeRepo createRepo() {
        return new RecipeRepo();
    }
}
