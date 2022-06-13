package com.thinhlh.mi_recipe.view.recipe_detail;

import android.os.Bundle;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.domain.repository.recipe.Direction;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentRecipeDetailBinding;
import com.thinhlh.mi_recipe.view.recipe_detail.adapters.DirectionAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.adapters.IngredientAdapter;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class RecipeDetailFragment extends BaseFragment<FragmentRecipeDetailBinding, RecipeDetailVM> implements RecipeDetailUV {

    private static final String RECIPE_KEY = "RECIPE_KEY";

    public static RecipeDetailFragment getInstance(Recipe recipe) {
        var instance = new RecipeDetailFragment();
        var bundle = new Bundle();

        bundle.putSerializable(RECIPE_KEY, recipe);

        instance.setArguments(bundle);

        return instance;
    }

    private IngredientAdapter ingredientAdapter;
    private DirectionAdapter directionAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_recipe_detail;
    }

    @Override
    protected Class<RecipeDetailVM> viewModelClass() {
        return RecipeDetailVM.class;
    }

    @Override
    protected void initViewModel(RecipeDetailVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        initCarousel();

        initAdapters();
    }

    private void initCarousel() {
        var carousel = binding.carousel;
        carousel.registerLifecycle(getLifecycle());

        var carouselData = new ArrayList<CarouselItem>() {{

        }};

        carousel.setData(carouselData);
    }

    private void initAdapters() {
        ingredientAdapter = new IngredientAdapter((item, adapterPosition) -> {

        });

        directionAdapter = new DirectionAdapter((item, adapterPosition) -> {

        });

        binding.ingredientRv.setAdapter(ingredientAdapter);
        binding.directionRv.setAdapter(directionAdapter);

        binding.ingredientRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_M));
//        binding.directionRv.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_M));
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            Recipe recipe = (Recipe) getArguments().getSerializable(RECIPE_KEY);
            viewModel.recipe.postValue(recipe);
            directionAdapter.submitList(recipe.getDirections());
            ingredientAdapter.submitList(recipe.getIngredients());
            binding.carousel.addData(new CarouselItem(recipe.getThumbnail()));
        }

    }

    @Override
    protected void initAction() {

    }
}
