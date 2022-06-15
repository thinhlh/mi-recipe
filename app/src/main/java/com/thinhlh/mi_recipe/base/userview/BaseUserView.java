package com.thinhlh.mi_recipe.base.userview;

import androidx.annotation.NonNull;

import com.thinhlh.domain.repository.recipe.Recipe;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public interface BaseUserView {
    void showLoading(@NonNull Boolean show);

    void onFragmentBackPressed();
}
