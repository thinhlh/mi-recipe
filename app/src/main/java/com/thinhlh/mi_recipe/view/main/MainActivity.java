package com.thinhlh.mi_recipe.view.main;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.activity.BaseFragmentBindingActivity;
import com.thinhlh.mi_recipe.base.fragment.FragmentNavigator;
import com.thinhlh.mi_recipe.databinding.ActivityMainBinding;
import com.thinhlh.mi_recipe.view.home.HomeFragment;
import com.thinhlh.mi_recipe.view.landing.LandingFragment;
import com.thinhlh.mi_recipe.view.login.LoginFragment;

/**
 * Created by thinhlh on 06/03/2022.
 * Copyright (c). All rights reserved
 */
public class MainActivity extends BaseFragmentBindingActivity<ActivityMainBinding, MainVM> implements MainUV {
    @Override
    protected Integer layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        getNavigator().setRootFragment(new LoginFragment());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initActions() {
        viewModel.initRetrofitService();
    }

    @Override
    protected Class<MainVM> viewModelClass() {
        return MainVM.class;
    }

    @Override
    protected void initViewModel(MainVM viewModel) {
        viewModel.init(this);
    }

    @Override
    protected FragmentNavigator createFragmentNavigator() {
        return new FragmentNavigator(getSupportFragmentManager(), R.id.fragment_container_view_tag);
    }

    @Override
    public void apiTimeOut() {
        showError("API Timeout", () -> {

        });
    }
}
