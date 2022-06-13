package com.thinhlh.domain.api.services;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.recipe.Ingredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IngredientService {

    @GET("ingredients")
    Call<BaseResponse<List<Ingredient>>> getAllIngredients();

}
