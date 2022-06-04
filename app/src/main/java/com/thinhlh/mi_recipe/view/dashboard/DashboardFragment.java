package com.thinhlh.mi_recipe.view.dashboard;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentDashboardBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.Category;
import com.thinhlh.mi_recipe.view.dashboard.adapter.CategoryAdapter;
import com.thinhlh.mi_recipe.view.dashboard.adapter.Recipe;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;

import java.util.ArrayList;

public class DashboardFragment extends BaseFragment<FragmentDashboardBinding, DashboardVM> implements DashboardUV {

    private CategoryAdapter categoryAdapter;
    private RecipeAdapter recipeAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected Class<DashboardVM> viewModelClass() {
        return DashboardVM.class;
    }

    @Override
    protected void initViewModel(DashboardVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        categoryAdapter = new CategoryAdapter((item, adapterPosition) -> {

        });

        binding.categoryRv.setAdapter(categoryAdapter);

        recipeAdapter = new RecipeAdapter(((item, adapterPosition) -> {

        }), R.layout.item_dashboard_recipe);
        binding.recipeRv.setAdapter(recipeAdapter);

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

        recipeAdapter.submitList(new ArrayList<>() {{
            add(new Recipe("Title", "Subtitle", 200, "Thumbnail"));
            add(new Recipe("Title", "Subtitle", 200, "Thumbnail"));
            add(new Recipe("Title", "Subtitle", 200, "Thumbnail"));
            add(new Recipe("Title", "Subtitle", 200, "Thumbnail"));
            add(new Recipe("Title", "Subtitle", 200, "Thumbnail"));
            add(new Recipe("Title", "Subtitle", 200, "Thumbnail"));
        }});
    }

    @Override
    protected void initAction() {

    }
}
