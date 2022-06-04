package com.thinhlh.mi_recipe.view.explorer.tabs.chief_choice;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentExplorerChiefChoiceBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.Recipe;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;

import java.util.ArrayList;

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

        }), R.layout.item_explorer_chief_recipe);
        binding.recipeRv.setLayoutManager(new LinearLayoutManager(fragmentContext) {

            @Override
            public void setOrientation(int orientation) {
                super.setOrientation(LinearLayoutManager.HORIZONTAL);
            }

            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() * 9 / 10;
                return true;
            }
        });
        binding.recipeRv.setAdapter(recipeAdapter);
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
