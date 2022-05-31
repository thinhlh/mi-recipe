package com.thinhlh.mi_recipe.base.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 * <p>
 * This is Base Repository View Model, used for view models that need to interact with repository
 */
public abstract class BaseRepoViewModel<R extends BaseRepo, V extends BaseUserView> extends BaseUiViewModel<V> {

    @Nullable
    private R repo;

    protected abstract R createRepo();

    @NonNull
    public R getRepo() {
        if (repo == null) {
            repo = createRepo();
        }
        return repo;
    }
}

