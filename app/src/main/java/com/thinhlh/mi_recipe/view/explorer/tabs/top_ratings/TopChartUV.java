package com.thinhlh.mi_recipe.view.explorer.tabs.top_ratings;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface TopChartUV extends BaseUserView {
    void updateRecipes(List<Recipe> recipes);
}
