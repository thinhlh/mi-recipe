package com.thinhlh.mi_recipe.view.landing.landing_1;


import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentLanding1Binding;

public class Landing1Fragment extends BaseFragment<FragmentLanding1Binding, Landing1VM> implements Landing1UV {
    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_landing_1;
    }

    @Override
    protected Class<Landing1VM> viewModelClass() {
        return Landing1VM.class;
    }

    @Override
    protected void initViewModel(Landing1VM viewModel) {
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
