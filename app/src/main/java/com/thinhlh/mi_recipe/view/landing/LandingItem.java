package com.thinhlh.mi_recipe.view.landing;

import androidx.annotation.DrawableRes;

public class LandingItem {
    private final String title;
    private final String content;
    private final @DrawableRes
    Integer image;

    public LandingItem(String title, String content, Integer image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public Integer getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
