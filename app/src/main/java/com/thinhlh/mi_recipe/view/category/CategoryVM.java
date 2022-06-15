package com.thinhlh.mi_recipe.view.category;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.category.CategoryRepo;
import com.thinhlh.domain.repository.recipe.GetRecipesType;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;

import java.util.List;

public class CategoryVM extends BaseRepoViewModel<RecipeRepo, CategoryUV> {

    public MutableLiveData<Category> category = new MutableLiveData<>();

    public void getRecipesOfCategory() {
        getRepo().getRecipes(GetRecipesType.ALL, new BaseRepoCallback<>() {
            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Recipe>> data) {
                uiCallback.updateRecipes(data.getData());
            }
        }, category.getValue() == null ? null : category.getValue().getId());
    }

    public void onBackPressedClick() {
        uiCallback.onFragmentBackPressed();
    }

    @Override
    protected RecipeRepo createRepo() {
        return new RecipeRepo();
    }
}
