package com.thinhlh.domain.repository.recipe;

import com.google.gson.annotations.SerializedName;

public class Direction {
    @SerializedName("content")
    private String content;
    @SerializedName("order")
    private Integer order;

    public Direction(String title, Integer order) {
        this.content = title;
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public Integer getOrder() {
        return order;
    }
}

