package com.thinhlh.mi_recipe.view.explorer.tabs.all_recipes;

import android.graphics.Color;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentAllRecipesBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.Recipe;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;

import java.util.ArrayList;

public class AllRecipesFragment extends BaseFragment<FragmentAllRecipesBinding, AllRecipesVM> implements AllRecipesUV {

    private RecipeAdapter recipeAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_all_recipes;
    }

    @Override
    protected Class<AllRecipesVM> viewModelClass() {
        return AllRecipesVM.class;
    }

    @Override
    protected void initViewModel(AllRecipesVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        recipeAdapter = new RecipeAdapter((item, adapterPosition) -> {

        }, R.layout.item_recipe_small);

        setUpRecyclerView(binding.quickCookRv);
        setUpRecyclerView(binding.premiumRv);
        setUpRecyclerView(binding.noCaloriesRv);
        setUpRecyclerView(binding.dessertRv);
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.addItemDecoration(new SpacingItemDecoration(fragmentContext, LinearLayout.HORIZONTAL, R.dimen.pad_M));
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
