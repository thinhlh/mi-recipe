package com.thinhlh.mi_recipe.view.category;

import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentCategoryBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.RecipeDetailFragment;

import java.util.List;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding, CategoryVM> implements CategoryUV {

    private final Category category;

    private RecipeAdapter recipeAdapter;

    public CategoryFragment(Category category) {
        this.category = category;
    }

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_category;
    }

    @Override
    protected Class<CategoryVM> viewModelClass() {
        return CategoryVM.class;
    }

    @Override
    protected void initViewModel(CategoryVM viewModel) {
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
        viewModel.category.setValue(category);
        viewModel.getRecipesOfCategory();
    }

    @Override
    public void updateRecipes(List<Recipe> recipes) {
        showLoading(false);
        recipeAdapter.submitList(recipes);
    }

    @Override
    protected void initAction() {

    }
}
