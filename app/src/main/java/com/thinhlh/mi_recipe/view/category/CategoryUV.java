package com.thinhlh.mi_recipe.view.category;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface CategoryUV extends BaseUserView {
    void updateRecipes(List<Recipe> recipes);
}
