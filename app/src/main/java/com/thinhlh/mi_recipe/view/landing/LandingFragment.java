package com.thinhlh.mi_recipe.view.landing;


import android.annotation.SuppressLint;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentLandingBinding;
import com.thinhlh.mi_recipe.view.login.LoginFragment;

import java.util.ArrayList;

public class LandingFragment extends BaseFragment<FragmentLandingBinding, LandingVM> implements LandingUV {

    private LandingPagerAdapter landingPagerAdapter;
    private RecyclerView.SmoothScroller smoothScroller;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_landing;
    }

    @Override
    protected Class<LandingVM> viewModelClass() {
        return LandingVM.class;
    }

    @Override
    protected void initViewModel(LandingVM viewModel) {
        binding.setVm(viewModel);
        viewModel.init(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        smoothScroller = new LinearSmoothScroller(fragmentContext);
        landingPagerAdapter = new LandingPagerAdapter(null);
        binding.recyclerView.setAdapter(landingPagerAdapter);
        binding.recyclerView.setOnTouchListener((view, motionEvent) -> true);
        new LinearSnapHelper().attachToRecyclerView(binding.recyclerView);
    }

    @Override
    protected void initData() {
        var landingContent = new ArrayList<LandingItem>() {{
            add(new LandingItem("Get inspired", "Browse more than 1000+ unique recipes of different cuisines from the best chefs", R.drawable.landing_3));
            add(new LandingItem("Sharpen your skills", "With out cooking videos and top tips", R.drawable.landing_4));
            add(new LandingItem("Share your recipes", "With our international community", R.drawable.landing_5));
        }};

        landingPagerAdapter.submitList(landingContent);
    }

    @Override
    protected void initAction() {

    }

    @Override
    public void onButtonClick() {
        var layoutManager = (LinearLayoutManager) binding.recyclerView.getLayoutManager();
        if (layoutManager != null) {
            var currentPosition = layoutManager.findFirstVisibleItemPosition();
            if (currentPosition == landingPagerAdapter.getItemCount() - 1) {
                goToLoginFragment();
            } else {
                var nextPosition = currentPosition + 1;
                viewModel.currentPage.postValue(nextPosition);
                smoothScroller.setTargetPosition(nextPosition);
                layoutManager.startSmoothScroll(smoothScroller);
            }
        }
    }

    @Override
    public void goToLoginFragment() {
        getNavigator().goTo(new LoginFragment());
    }
}
