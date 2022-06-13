package com.thinhlh.mi_recipe.base.userview;

import androidx.annotation.NonNull;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public interface BaseUserView {
    void showLoading(@NonNull Boolean show);

    void onFragmentBackPressed();
}
