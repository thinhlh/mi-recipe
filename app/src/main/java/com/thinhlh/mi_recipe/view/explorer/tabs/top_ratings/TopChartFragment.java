package com.thinhlh.mi_recipe.view.explorer.tabs.top_ratings;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentTopChartBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.Category;
import com.thinhlh.mi_recipe.view.dashboard.adapter.CategoryAdapter;
import com.thinhlh.mi_recipe.view.dashboard.adapter.Recipe;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;

import java.util.ArrayList;

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

        }, R.layout.item_recipe_top_chart);

        binding.categoryRv.setAdapter(recipeAdapter);
    }

    @Override
    protected void initData() {
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
