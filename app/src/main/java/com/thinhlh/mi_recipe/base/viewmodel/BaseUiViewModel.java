package com.thinhlh.mi_recipe.base.viewmodel;

import androidx.annotation.NonNull;

import com.thinhlh.mi_recipe.base.userview.BaseUserView;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 * <p>
 * This is BaseUiViewModel, only used for screens that does not interact with Repository
 */
public abstract class BaseUiViewModel<V extends BaseUserView> extends BaseViewModel {
    protected V uiCallback;

    public void init(@NonNull V uiCallback) {
        this.uiCallback = uiCallback;
    }
}
