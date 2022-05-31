package com.thinhlh.mi_recipe.view.home;


import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.fragment.TransactionDirection;
import com.thinhlh.mi_recipe.databinding.FragmentHomeBinding;
import com.thinhlh.mi_recipe.view.landing.LandingFragment;

/**
 * Created by thinhlh on 06/03/2022.
 * Copyright (c). All rights reserved
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeVM> implements HomeUV {

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected Class<HomeVM> viewModelClass() {
        return HomeVM.class;
    }

    @Override
    protected void initViewModel(HomeVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    public void updateData(String newData) {
        binding.text.setText(newData);
        getNavigator().goTo(new LandingFragment(), TransactionDirection.RTL);
    }
}
