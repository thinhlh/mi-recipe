package com.thinhlh.mi_recipe.view.recipe_detail;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

public class RecipeDetailVM extends BaseRepoViewModel<RecipeRepo, RecipeDetailUV> {
    public MutableLiveData<Recipe> recipe = new MutableLiveData<>();

    public void onBackPressed() {
        uiCallback.onFragmentBackPressed();
    }

    public void toggleSave() {
        if (recipe.getValue() == null) return;
        getRepo().toggleSaveRecipe(recipe.getValue().getId(), data -> {
            uiCallback.updateSave(data.getData());
        });
    }

    @Override
    protected RecipeRepo createRepo() {
        return new RecipeRepo();
    }
}
