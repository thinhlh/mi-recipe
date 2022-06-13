package com.thinhlh.domain.api.services;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.category.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("categories")
    Call<BaseResponse<List<Category>>> getCategories();
}
