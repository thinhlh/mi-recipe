package com.thinhlh.mi_recipe.view.dashboard.adapter;

public class Recipe {
    private final String title;
    private final String subtitle;
    private final Integer favorites;
    private final String thumbnail;

    public Recipe(String title, String subtitle, Integer favorites, String thumbnail) {
        this.title = title;
        this.subtitle = subtitle;
        this.favorites = favorites;
        this.thumbnail = thumbnail;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
