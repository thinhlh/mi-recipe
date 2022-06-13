package com.thinhlh.mi_recipe.view.create_recipe;

import com.thinhlh.domain.repository.recipe.Direction;
import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface CreateRecipeUV extends BaseUserView {
    void openImagePicker();

    void updateIngredients(List<Ingredient> ingredients);

    String getRecipeTitle();
    String getRecipeDescription();
    Integer getRecipeCalories();
    Integer getRecipeTakenTime();
    Integer getPeople();
    List<Ingredient> getIngredients();
    List<Direction> getDirections();
}
