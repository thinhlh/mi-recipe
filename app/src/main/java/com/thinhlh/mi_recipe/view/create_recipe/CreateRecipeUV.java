package com.thinhlh.mi_recipe.view.create_recipe;

import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.recipe.CreateRecipeRequest;
import com.thinhlh.domain.repository.recipe.Direction;
import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface CreateRecipeUV extends BaseUserView {
    void openImagePicker();

    void onFragmentBackPressed(Recipe recipe);

    void updateIngredients(List<Ingredient> ingredients);

    void updateCategories(List<Category> categories);

    String getRecipeTitle() throws Exception;

    String getRecipeDescription() throws Exception;

    Integer getRecipeCalories() throws Exception;

    Integer getRecipeTakenTime() throws Exception;

    Integer getPeople() throws Exception;

    List<Ingredient> getIngredients();

    List<String> getDirections();

    List<Category> getCategories();

    void uploadThumbnail(CreateRecipeRequest request);
}
