package com.thinhlh.mi_recipe.view.create_recipe.adapter;

import com.thinhlh.domain.repository.recipe.Ingredient;

public class CreateIngredient {
    private String id;
    private String title;
    private Integer quantity;

    private Boolean isFinalIngredientInList = true;

    public CreateIngredient(String id, String title, Integer quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }

    public CreateIngredient(){

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
