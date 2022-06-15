package com.thinhlh.mi_recipe.view.saved_recipe;

import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentSavedRecipeBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.RecipeDetailFragment;

import java.util.List;

public class SavedRecipeFragment extends BaseFragment<FragmentSavedRecipeBinding, SavedRecipeVM> implements SavedRecipeUV {

    private RecipeAdapter recipeAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_saved_recipe;
    }

    @Override
    protected Class<SavedRecipeVM> viewModelClass() {
        return SavedRecipeVM.class;
    }

    @Override
    protected void initViewModel(SavedRecipeVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        recipeAdapter = new RecipeAdapter(((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }), R.layout.item_category_recipe);
        binding.recipeRv.setAdapter(recipeAdapter);
    }

    @Override
    protected void initData() {
        viewModel.getSavedRecipe();
    }

    @Override
    protected void initAction() {

    }

    @Override
    public void updateRecipes(List<Recipe> recipes) {
        showLoading(false);
        recipeAdapter.submitList(recipes);
    }
}
