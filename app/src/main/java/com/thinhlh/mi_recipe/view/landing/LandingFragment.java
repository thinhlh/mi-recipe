package com.thinhlh.mi_recipe.view.landing;


import android.util.Log;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentLandingBinding;

public class LandingFragment extends BaseFragment<FragmentLandingBinding, LandingVM> implements LandingUV {
    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_landing;
    }

    @Override
    protected Class<LandingVM> viewModelClass() {
        return LandingVM.class;
    }

    @Override
    protected void initViewModel(LandingVM viewModel) {
        viewModel.init(this);
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
}
