package com.thinhlh.mi_recipe.view.saved_recipe;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface SavedRecipeUV extends BaseUserView {
    void updateRecipes(List<Recipe> recipes);
}
