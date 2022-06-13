package com.thinhlh.mi_recipe.view.recipe_detail;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

public class RecipeDetailVM extends BaseUiViewModel<RecipeDetailUV> {
    public MutableLiveData<Recipe> recipe = new MutableLiveData<>();

    public void onBackPressed() {
        uiCallback.onFragmentBackPressed();
    }
}
