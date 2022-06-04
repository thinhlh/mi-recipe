package com.thinhlh.mi_recipe.view.home;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentHomeBinding;
import com.thinhlh.mi_recipe.view.dashboard.DashboardFragment;
import com.thinhlh.mi_recipe.view.explorer.ExplorerFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeVM> implements HomeUV {

    private final List<Fragment> fragments = new ArrayList<>() {{
        add(new DashboardFragment());
        add(new ExplorerFragment());
    }};

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected Class<HomeVM> viewModelClass() {
        return HomeVM.class;
    }

    @Override
    protected void initViewModel(HomeVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
        showOrAddFragment(binding.fragmentContainerViewTag.getId(), fragments.get(0));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAction() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    showOrAddFragment(binding.fragmentContainerViewTag.getId(), fragments.get(0));
                    break;
                case R.id.search:
                    showOrAddFragment(binding.fragmentContainerViewTag.getId(), fragments.get(1));
                    break;
                default:
                    break;
            }

            return true;
        });
    }
}
