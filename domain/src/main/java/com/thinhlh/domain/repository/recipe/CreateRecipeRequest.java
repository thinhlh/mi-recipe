package com.thinhlh.domain.repository.recipe;


import com.google.gson.annotations.SerializedName;

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

    @SerializedName("ingredients")
    private List<Ingredient> ingredients;

    @SerializedName("directions")
    private List<Direction> directions;

    public static class Builder {

        private String title;
        private String description;
        private Integer calories;
        private Integer takenTime;
        private List<Ingredient> ingredients = new ArrayList<>();
        private List<Direction> directions = new ArrayList<>();

        public Builder() {
        }

        Builder(String title, String description, Integer calories, Integer takenTime, List<Ingredient> ingredients, List<Direction> directions) {
            this.title = title;
            this.description = description;
            this.calories = calories;
            this.takenTime = takenTime;
            this.ingredients = ingredients;
            this.directions = directions;
        }

        public Builder title(String title){
            this.title = title;
            return Builder.this;
        }

        public Builder description(String description){
            this.description = description;
            return Builder.this;
        }

        public Builder calories(Integer calories){
            this.calories = calories;
            return Builder.this;
        }

        public Builder takenTime(Integer takenTime){
            this.takenTime = takenTime;
            return Builder.this;
        }

        public Builder ingredients(List<Ingredient> ingredients){
            this.ingredients = ingredients;
            return Builder.this;
        }

        public Builder addIngredients(Ingredient ingredients){
            this.ingredients.add(ingredients);
            return Builder.this;
        }

        public Builder directions(List<Direction> directions){
            this.directions = directions;
            return Builder.this;
        }

        public Builder addDirections(Direction directions){
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
        this.ingredients = builder.ingredients;
        this.directions = builder.directions;
    }
}
