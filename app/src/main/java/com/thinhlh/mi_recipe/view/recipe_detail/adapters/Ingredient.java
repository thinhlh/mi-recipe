package com.thinhlh.mi_recipe.view.recipe_detail.adapters;

public class Ingredient {
    private String title;
    private Integer quantity;
    private String unit;

    public Ingredient(String title, Integer quantity, String unit) {
        this.title = title;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
}
