package com.thinhlh.mi_recipe.base.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.thinhlh.mi_recipe.base.viewmodel.BaseViewModel;
import com.thinhlh.mi_recipe.data.AppConst;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public abstract class BaseFullscreenActivity<T extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity<T, VM> {
    private final Boolean runInFullScreen = AppConst.RUN_IN_FULLSCREEN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (runInFullScreen) {
            if (getWindow().getDecorView() != null) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            }
        }

        super.onCreate(savedInstanceState);
    }
}
