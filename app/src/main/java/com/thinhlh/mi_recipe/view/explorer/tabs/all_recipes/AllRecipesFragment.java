package com.thinhlh.mi_recipe.view.explorer.tabs.all_recipes;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentAllRecipesBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.RecipeDetailFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllRecipesFragment extends BaseFragment<FragmentAllRecipesBinding, AllRecipesVM> implements AllRecipesUV {

    private RecipeAdapter quickCookAdapter;
    private RecipeAdapter premiumAdapter;
    private RecipeAdapter noCaloriesAdapter;
    private RecipeAdapter searchRecipeAdapter;

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
        searchRecipeAdapter = new RecipeAdapter((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }, R.layout.item_category_recipe);

        quickCookAdapter = new RecipeAdapter((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }, R.layout.item_recipe_small);

        premiumAdapter = new RecipeAdapter((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }, R.layout.item_recipe_small);

        noCaloriesAdapter = new RecipeAdapter((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }, R.layout.item_recipe_small);

        setUpRecyclerView(binding.quickCookRv, quickCookAdapter);
        setUpRecyclerView(binding.premiumRv, premiumAdapter);
        setUpRecyclerView(binding.noCaloriesRv, noCaloriesAdapter);
        setUpRecyclerView(binding.searchRecipeRv, searchRecipeAdapter);
    }

    private void setUpRecyclerView(RecyclerView recyclerView, RecipeAdapter recipeAdapter) {
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.addItemDecoration(new SpacingItemDecoration(fragmentContext, LinearLayout.HORIZONTAL, R.dimen.pad_M));
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        viewModel.getAllRecipes();
    }

    @Override
    protected void initAction() {
        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    binding.searchRecipeRv.setVisibility(View.GONE);
                    binding.list.setVisibility(View.VISIBLE);
                } else {
                    searchRecipeAdapter
                            .submitList(
                                    viewModel
                                            .recipes
                                            .stream()
                                            .filter((it) -> it.getTitle().contains(editable.toString()))
                                            .collect(Collectors.toList()));

                    binding.searchRecipeRv.setVisibility(View.VISIBLE);
                    binding.list.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void updateData(List<Recipe> recipes) {
        quickCookAdapter.submitList(recipes.stream().filter((it) -> it.getTakenTime() < 30).collect(Collectors.toList()));
        premiumAdapter.submitList(recipes.stream().filter((it) -> it.getRating() > 3).collect(Collectors.toList()));
        noCaloriesAdapter.submitList(recipes.stream().filter((it) -> it.getCalories() < 50).collect(Collectors.toList()));
    }
}
