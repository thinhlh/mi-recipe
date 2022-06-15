package com.thinhlh.mi_recipe.view.my_recipes;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface MyRecipesUV extends BaseUserView {
    void createRecipe();

    void updateMyRecipes(List<Recipe> recipes);
}
