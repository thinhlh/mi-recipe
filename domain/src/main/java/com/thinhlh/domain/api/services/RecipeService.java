package com.thinhlh.domain.api.services;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.recipe.CreateRecipeRequest;
import com.thinhlh.domain.repository.recipe.GetRecipesType;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.domain.repository.recipe.RecipeRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RecipeService {
    @GET("recipes")
    Call<BaseResponse<List<Recipe>>> getRecipes(@Query("type") GetRecipesType recipesType, @Query("categoryId") String categoryId);

    @POST("recipe/create")
    Call<BaseResponse<Recipe>> createRecipe(@Body CreateRecipeRequest request);

    @POST("recipe/save")
    Call<BaseResponse<Boolean>> toggleSaveRecipe(@Query("recipeId") String recipeId);
}
