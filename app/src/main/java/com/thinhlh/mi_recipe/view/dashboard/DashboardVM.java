package com.thinhlh.mi_recipe.view.dashboard;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.category.CategoryRepo;
import com.thinhlh.domain.repository.recipe.GetRecipesType;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;

import java.util.List;

public class DashboardVM extends BaseRepoViewModel<CategoryRepo, DashboardUV> {

    private final RecipeRepo recipeRepo = new RecipeRepo();

    public void getCategories() {
        showLoading(true);
        getRepo().getCategories(new BaseRepoCallback<>() {

            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Category>> data) {
                uiCallback.updateCategories(data.getData());
                getPopularRecipes();
            }
        });
    }

    public void getPopularRecipes() {

        recipeRepo.getRecipes(GetRecipesType.POPULAR, new BaseRepoCallback<>() {

            @Override
            public void apiResponse(BaseResponse<List<Recipe>> data) {
                var recipes = data.getData();
                uiCallback.updatePopularRecipes(recipes.subList(0,Math.min(3,recipes.size())));
                showLoading(false);
            }
        });
    }

    @Override
    protected CategoryRepo createRepo() {
        return new CategoryRepo();
    }
}
