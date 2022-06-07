package com.thinhlh.mi_recipe.view.recipe_detail;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentRecipeDetailBinding;
import com.thinhlh.mi_recipe.view.recipe_detail.adapters.Direction;
import com.thinhlh.mi_recipe.view.recipe_detail.adapters.DirectionAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.adapters.Ingredient;
import com.thinhlh.mi_recipe.view.recipe_detail.adapters.IngredientAdapter;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator2;

public class RecipeDetailFragment extends BaseFragment<FragmentRecipeDetailBinding, RecipeDetailVM> implements RecipeDetailUV {

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
            add(new CarouselItem(R.drawable.landing_1));

            add(new CarouselItem(R.drawable.landing_2));

            add(new CarouselItem(R.drawable.landing_3));

            add(new CarouselItem(R.drawable.landing_4));

            add(new CarouselItem(R.drawable.landing_5));
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
        ingredientAdapter.submitList(new ArrayList<>() {{
            add(new Ingredient("Title", 1, "pc"));
            add(new Ingredient("Title", 1, "pc"));
            add(new Ingredient("Title", 1, "pc"));
            add(new Ingredient("Title", 1, "pc"));
        }});

        directionAdapter.submitList(new ArrayList<>() {{
            add(new Direction("Title", 1));
            add(new Direction("Title", 1));
            add(new Direction("Title", 1));
        }});
    }

    @Override
    protected void initAction() {

    }
}
