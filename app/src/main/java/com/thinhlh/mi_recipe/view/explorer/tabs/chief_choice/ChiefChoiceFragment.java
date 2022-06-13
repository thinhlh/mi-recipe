package com.thinhlh.mi_recipe.view.explorer.tabs.chief_choice;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentExplorerChiefChoiceBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.RecipeDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class ChiefChoiceFragment extends BaseFragment<FragmentExplorerChiefChoiceBinding, ChiefChoiceVM> implements ChiefChoiceUV {

    private RecipeAdapter recipeAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_explorer_chief_choice;
    }

    @Override
    protected Class<ChiefChoiceVM> viewModelClass() {
        return ChiefChoiceVM.class;
    }

    @Override
    protected void initViewModel(ChiefChoiceVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        recipeAdapter = new RecipeAdapter(((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }), R.layout.item_explorer_chief_recipe);

        binding.recipeRv.setLayoutManager(new LinearLayoutManager(fragmentContext) {

            @Override
            public void setOrientation(int orientation) {
                super.setOrientation(LinearLayoutManager.HORIZONTAL);
            }

            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = (int) (getWidth() * 8.5 / 10);
                return true;
            }
        });
        binding.recipeRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.HORIZONTAL, R.dimen.pad_M));
        binding.recipeRv.setAdapter(recipeAdapter);
    }

    @Override
    protected void initData() {
        viewModel.getChiefChoiceRecipes();
    }

    @Override
    protected void initAction() {

    }

    @Override
    public void updateRecipes(List<Recipe> recipes) {
        recipeAdapter.submitList(recipes);
    }
}
