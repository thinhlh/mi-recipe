package com.thinhlh.mi_recipe.view.my_recipes;

import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

public class MyRecipesVM extends BaseUiViewModel<MyRecipesUV> {
    public void createRecipe(){
        uiCallback.createRecipe();
    }
}
