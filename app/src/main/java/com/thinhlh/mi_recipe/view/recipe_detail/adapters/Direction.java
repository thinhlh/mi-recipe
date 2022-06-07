package com.thinhlh.mi_recipe.view.recipe_detail.adapters;

public class Direction {
    private String title;
    private Integer order;

    public Direction(String title, Integer order) {
        this.title = title;
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public Integer getOrder() {
        return order;
    }
}
