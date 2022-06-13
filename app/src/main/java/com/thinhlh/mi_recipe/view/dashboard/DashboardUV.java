package com.thinhlh.mi_recipe.view.dashboard;

import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface DashboardUV extends BaseUserView {
    void updateCategories(List<Category> categories);

    void updatePopularRecipes(List<Recipe> recipes);
}
