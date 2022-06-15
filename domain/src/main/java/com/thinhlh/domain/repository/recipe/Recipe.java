package com.thinhlh.domain.repository.recipe;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinhlh.domain.repository.category.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Recipe implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("categories")
    private List<Category> categories;

    @SerializedName("people")
    private Integer people;

    @SerializedName("takenTime")
    private Integer takenTime;

    @SerializedName("directions")
    private List<Direction> directions;

    @SerializedName("ingredients")
    private List<Ingredient> ingredients;

    private Double rating;

    @SerializedName("calories")
    private Double calories;

    @SerializedName("userSaved")
    private Integer userSaved;


    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getId() {
        return id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getPeople() {
        return people;
    }

    public Integer getTakenTime() {
        return takenTime;
    }

    public Double getCalories() {
        return calories;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Integer getUserSaved() {
        return userSaved;
    }
}
