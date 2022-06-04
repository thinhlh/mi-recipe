package com.thinhlh.mi_recipe.view.explorer.tabs.popular;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentExplorerPopularBinding;

public class ExplorerPopularFragment extends BaseFragment<FragmentExplorerPopularBinding, ExplorerPopularVM> implements ExplorerPopularUV {
    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_explorer_popular;
    }

    @Override
    protected Class<ExplorerPopularVM> viewModelClass() {
        return ExplorerPopularVM.class;
    }

    @Override
    protected void initViewModel(ExplorerPopularVM viewModel) {
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
}
