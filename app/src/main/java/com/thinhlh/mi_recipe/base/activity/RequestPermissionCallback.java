package com.thinhlh.mi_recipe.base.activity;

/**
 * Created by thinhlh on 04/03/2022.
 * Copyright (c). All rights reserved
 */
public interface RequestPermissionCallback {
    void onPermissionGranted();

    void onPermissionDenied();

}
