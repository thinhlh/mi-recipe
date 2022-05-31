package com.thinhlh.mi_recipe.base.activity;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.fragment.FragmentNavigator;
import com.thinhlh.mi_recipe.base.viewmodel.BaseViewModel;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public abstract class BaseFragmentBindingActivity<T extends ViewDataBinding, VM extends BaseViewModel> extends BaseFullscreenActivity<T, VM> {

    protected abstract FragmentNavigator createFragmentNavigator();

    @Nullable
    private FragmentNavigator navigator;

    public FragmentNavigator getNavigator() {
        if (navigator == null) {
            navigator = createFragmentNavigator();
        }
        return navigator;
    }

    @Override
    public void onBackPressed() {
        if (navigator != null) {
            if (navigator.size() > 0) {
                if (navigator.getActiveFragment() instanceof BaseFragment) {
                    ((BaseFragment) navigator.getActiveFragment()).onFragmentBackPressed();
                }
            }
        }
        super.onBackPressed();
    }
}
