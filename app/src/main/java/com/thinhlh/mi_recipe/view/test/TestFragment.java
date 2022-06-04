package com.thinhlh.mi_recipe.view.test;


import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.fragment.TransactionDirection;
import com.thinhlh.mi_recipe.databinding.FragmentTestBinding;
import com.thinhlh.mi_recipe.view.landing.LandingFragment;

public class TestFragment extends BaseFragment<FragmentTestBinding, TestVM> implements TestUV {

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_test;
    }

    @Override
    protected Class<TestVM> viewModelClass() {
        return TestVM.class;
    }

    @Override
    protected void initViewModel(TestVM viewModel) {
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
