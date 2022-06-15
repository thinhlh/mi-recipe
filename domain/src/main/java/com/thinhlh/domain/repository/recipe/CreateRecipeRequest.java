package com.thinhlh.domain.repository.recipe;


import com.google.gson.annotations.SerializedName;
import com.thinhlh.domain.repository.category.Category;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeRequest {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("calories")
    private Integer calories;

    @SerializedName("takenTime")
    private Integer takenTime;

    @SerializedName("people")
    private Integer people;

    @SerializedName("categories")
    private List<Category> categories;

    @SerializedName("ingredients")
    private List<Ingredient> ingredients;

    @SerializedName("directions")
    private List<String> directions;

    @SerializedName("thumbnail")
    private String thumbnail;

    public static class Builder {

        private String title;
        private String description;
        private Integer calories;
        private Integer takenTime;
        private Integer people;
        private String thumbnail;
        private List<Category> categories;
        private List<Ingredient> ingredients = new ArrayList<>();
        private List<String> directions = new ArrayList<>();

        public Builder() {
        }

        Builder(String title, String description, String thumbnail, Integer calories, Integer takenTime, Integer people, List<Category> categories, List<Ingredient> ingredients, List<String> directions) {
            this.title = title;
            this.description = description;
            this.calories = calories;
            this.thumbnail = thumbnail;
            this.takenTime = takenTime;
            this.people = people;
            this.categories = categories;
            this.ingredients = ingredients;
            this.directions = directions;
        }

        public Builder title(String title) {
            this.title = title;
            return Builder.this;
        }

        public Builder description(String description) {
            this.description = description;
            return Builder.this;
        }

        public Builder calories(Integer calories) {
            this.calories = calories;
            return Builder.this;
        }

        public Builder thumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return Builder.this;
        }

        public Builder takenTime(Integer takenTime) {
            this.takenTime = takenTime;
            return Builder.this;
        }

        public Builder people(Integer people) {
            this.people = people;
            return Builder.this;
        }

        public Builder categories(List<Category> categories) {
            this.categories = categories;
            return Builder.this;
        }

        public Builder ingredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
            return Builder.this;
        }

        public Builder addIngredients(Ingredient ingredients) {
            this.ingredients.add(ingredients);
            return Builder.this;
        }

        public Builder addCategories(Category category) {
            this.categories.add(category);
            return Builder.this;
        }

        public Builder directions(List<String> directions) {
            this.directions = directions;
            return Builder.this;
        }

        public Builder addDirections(String directions) {
            this.directions.add(directions);
            return Builder.this;
        }

        public CreateRecipeRequest build() {
            return new CreateRecipeRequest(this);
        }
    }

    private CreateRecipeRequest(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.calories = builder.calories;
        this.takenTime = builder.takenTime;
        this.people = builder.people;
        this.thumbnail = builder.thumbnail;
        this.categories = builder.categories;
        this.ingredients = builder.ingredients;
        this.directions = builder.directions;
    }

    public String getTitle() {
        return title;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }

    public Integer getPeople() {
        return people;
    }

    public Integer getTakenTime() {
        return takenTime;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCalories() {
        return calories;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
