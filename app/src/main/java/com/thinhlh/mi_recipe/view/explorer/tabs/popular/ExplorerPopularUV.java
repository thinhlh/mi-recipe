package com.thinhlh.mi_recipe.view.explorer.tabs.popular;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface ExplorerPopularUV extends BaseUserView {
    void updatePopularRecipes(List<Recipe> recipes);
}
