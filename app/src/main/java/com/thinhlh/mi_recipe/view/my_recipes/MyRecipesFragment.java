package com.thinhlh.mi_recipe.view.my_recipes;

import android.view.View;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentMyRecipesBinding;
import com.thinhlh.mi_recipe.view.create_recipe.CreateRecipeFragment;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;

import java.util.ArrayList;

public class MyRecipesFragment extends BaseFragment<FragmentMyRecipesBinding, MyRecipesVM> implements MyRecipesUV {

    private RecipeAdapter recipeAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_my_recipes;
    }

    @Override
    protected Class<MyRecipesVM> viewModelClass() {
        return MyRecipesVM.class;
    }

    @Override
    protected void initViewModel(MyRecipesVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        recipeAdapter = new RecipeAdapter((item, adapterPosition) -> {

        }, R.layout.item_explorer_chief_recipe);

        binding.recipeRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_L));
        binding.recipeRv.setAdapter(recipeAdapter);
    }

    @Override
    protected void initData() {
        binding.text.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initAction() {

    }

    @Override
    public void createRecipe() {
        getNavigator().goTo(new CreateRecipeFragment());
    }
}
