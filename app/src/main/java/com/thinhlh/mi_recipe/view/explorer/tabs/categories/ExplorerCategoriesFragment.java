package com.thinhlh.mi_recipe.view.explorer.tabs.categories;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentExplorerCategoriesBinding;
import com.thinhlh.mi_recipe.view.category.CategoryFragment;
import com.thinhlh.mi_recipe.view.dashboard.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExplorerCategoriesFragment extends BaseFragment<FragmentExplorerCategoriesBinding, ExplorerCategoriesVM> implements ExplorerCategoriesUV {

    private CategoryAdapter categoryAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_explorer_categories;
    }

    @Override
    protected Class<ExplorerCategoriesVM> viewModelClass() {
        return ExplorerCategoriesVM.class;
    }

    @Override
    protected void initViewModel(ExplorerCategoriesVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        categoryAdapter = new CategoryAdapter((item, adapterPosition) -> {
            getNavigator().goTo(new CategoryFragment(item));
        }, R.layout.item_explorer_category);
        binding.categoryRv.setAdapter(categoryAdapter);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        binding.categoryRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.HORIZONTAL, R.dimen.pad_M));
        binding.categoryRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_L));
    }

    @Override
    public void updateCategories(List<Category> categories) {
        categoryAdapter.submitList(categories);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        viewModel.getCategories();
    }

    @Override
    protected void initAction() {

    }
}
