package com.thinhlh.mi_recipe.view.explorer.tabs.all_recipes;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface AllRecipesUV extends BaseUserView {
    void updateData(List<Recipe> recipes);
}
