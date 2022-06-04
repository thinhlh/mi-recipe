package com.thinhlh.mi_recipe.view.dashboard.adapter;

public class Category {
    private final String name;
    private final String thumbnail;

    public Category(String name, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
