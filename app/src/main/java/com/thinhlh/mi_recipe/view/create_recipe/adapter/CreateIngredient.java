package com.thinhlh.mi_recipe.view.create_recipe.adapter;

import com.thinhlh.domain.repository.recipe.Ingredient;

public class CreateIngredient {
    private String id;
    private String title;
    private Integer quantity;
    private String unit;

    public CreateIngredient(String id, String title, Integer quantity, String unit) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.unit = unit;
    }

    public CreateIngredient() {

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

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
