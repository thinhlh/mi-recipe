package com.thinhlh.mi_recipe.view.explorer;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentExplorerBinding;
import com.thinhlh.mi_recipe.view.explorer.tabs.ExplorerViewPagerAdapter;
import com.thinhlh.mi_recipe.view.explorer.tabs.chief_choice.ChiefChoiceFragment;
import com.thinhlh.mi_recipe.view.explorer.tabs.popular.ExplorerPopularFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExplorerFragment extends BaseFragment<FragmentExplorerBinding, ExplorerVM> implements ExplorerUV {

    private final Map<String, Fragment> fragments = new LinkedHashMap<>() {{
        put("Popular", new ExplorerPopularFragment());
        put("Quick Cook", new ExplorerPopularFragment());
        put("Category", new ExplorerPopularFragment());
        put("Chief's choice", new ChiefChoiceFragment());
        put("My Recipes", new ExplorerPopularFragment());
    }};


    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_explorer;
    }

    @Override
    protected Class<ExplorerVM> viewModelClass() {
        return ExplorerVM.class;
    }

    @Override
    protected void initViewModel(ExplorerVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        ExplorerViewPagerAdapter pagerAdapter = new ExplorerViewPagerAdapter(getChildFragmentManager(), getLifecycle());
        binding.pager.setAdapter(pagerAdapter);
        binding.pager.setUserInputEnabled(false);


        fragments.forEach((title, fragment) -> pagerAdapter.addFragment(fragment));

        new TabLayoutMediator(binding.tabs, binding.pager, false, (tab, position) -> {
            tab.setText(new ArrayList<>(fragments.entrySet()).get(position).getKey());
        }).attach();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAction() {

    }
}
