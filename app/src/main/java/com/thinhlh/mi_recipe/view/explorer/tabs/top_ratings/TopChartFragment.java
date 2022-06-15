package com.thinhlh.mi_recipe.view.explorer.tabs.top_ratings;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentTopChartBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.RecipeDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class TopChartFragment extends BaseFragment<FragmentTopChartBinding, TopChartVM> implements TopChartUV {

    private RecipeAdapter recipeAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_top_chart;
    }

    @Override
    protected Class<TopChartVM> viewModelClass() {
        return TopChartVM.class;
    }

    @Override
    protected void initViewModel(TopChartVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        recipeAdapter = new RecipeAdapter((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }, R.layout.item_recipe_top_chart);

        binding.categoryRv.setAdapter(recipeAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        viewModel.getTopChartRecipes();
    }

    @Override
    protected void initAction() {

    }

    @Override
    public void updateRecipes(List<Recipe> recipes) {
        recipeAdapter.submitList(recipes);
    }
}
