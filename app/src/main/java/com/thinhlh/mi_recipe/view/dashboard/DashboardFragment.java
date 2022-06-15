package com.thinhlh.mi_recipe.view.dashboard;

import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentDashboardBinding;
import com.thinhlh.mi_recipe.view.category.CategoryFragment;
import com.thinhlh.mi_recipe.view.dashboard.adapter.CategoryAdapter;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.RecipeDetailFragment;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends BaseFragment<FragmentDashboardBinding, DashboardVM> implements DashboardUV {

    private CategoryAdapter categoryAdapter;
    private RecipeAdapter recipeAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected Class<DashboardVM> viewModelClass() {
        return DashboardVM.class;
    }

    @Override
    protected void initViewModel(DashboardVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        categoryAdapter = new CategoryAdapter((item, adapterPosition) -> {
            getNavigator().goTo(new CategoryFragment(item));
        }, R.layout.item_category);

        binding.categoryRv.setAdapter(categoryAdapter);

        recipeAdapter = new RecipeAdapter(((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }), R.layout.item_dashboard_recipe);
        binding.recipeRv.setAdapter(recipeAdapter);

        initCarousel();
    }

    @Override
    protected void initData() {
        viewModel.getCategories();
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

    @Override
    protected void initAction() {

    }

    @Override
    public void updateCategories(List<Category> categories) {
        categoryAdapter.submitList(categories);
    }

    @Override
    public void updatePopularRecipes(List<Recipe> recipes) {
        recipeAdapter.submitList(recipes);
    }
}
