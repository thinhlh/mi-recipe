package com.thinhlh.mi_recipe.view.explorer.tabs.chief_choice;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface ChiefChoiceUV extends BaseUserView {
    void updateRecipes(List<Recipe> recipes);
}
