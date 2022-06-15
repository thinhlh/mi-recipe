package com.thinhlh.domain.repository.recipe;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("quantity")
    private Integer quantity = 0;

    @SerializedName("unit")
    private String unit;

    public Ingredient(String id, String title, Integer quantity, String unit) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient(String title, Integer quantity, String unit) {
        this.title = title;
        this.quantity = quantity;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }
}