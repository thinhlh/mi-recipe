package com.thinhlh.mi_recipe.view.explorer.tabs.categories;

import android.graphics.Color;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentExplorerCategoriesBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.Category;
import com.thinhlh.mi_recipe.view.dashboard.adapter.CategoryAdapter;

import java.util.ArrayList;

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

        }, R.layout.item_explorer_category);
        binding.categoryRv.setAdapter(categoryAdapter);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        binding.categoryRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.HORIZONTAL, R.dimen.pad_M));
        binding.categoryRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_L));
    }

    @Override
    protected void initData() {
        categoryAdapter.submitList(new ArrayList<>() {{
            add(new Category("Dinner", "Thumbnail"));
            add(new Category("Dinner", "Thumbnail"));
            add(new Category("Dinner", "Thumbnail"));
            add(new Category("Dinner", "Thumbnail"));
            add(new Category("Dinner", "Thumbnail"));
            add(new Category("Dinner", "Thumbnail"));
        }});
    }

    @Override
    protected void initAction() {

    }
}
