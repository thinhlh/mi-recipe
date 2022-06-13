package com.thinhlh.domain.api.base;

import com.thinhlh.domain.api.RetrofitService;
import com.thinhlh.domain.api.services.AuthService;
import com.thinhlh.domain.api.services.CategoryService;
import com.thinhlh.domain.api.services.CommonService;
import com.thinhlh.domain.api.services.IngredientService;
import com.thinhlh.domain.api.services.RecipeService;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 * This is where we declare all services
 */
public abstract class BaseApi extends BaseApiError {
    protected CommonService commonService = RetrofitService.get().createService(CommonService.class);
    protected AuthService authService = RetrofitService.get().createService(AuthService.class);
    protected CategoryService categoryService = RetrofitService.get().createService(CategoryService.class);
    protected RecipeService recipeService = RetrofitService.get().createService(RecipeService.class);
    protected IngredientService ingredientService = RetrofitService.get().createService(IngredientService.class);
}
