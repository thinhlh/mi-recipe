package com.thinhlh.mi_recipe.view.home;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentHomeBinding;
import com.thinhlh.mi_recipe.view.dashboard.DashboardFragment;
import com.thinhlh.mi_recipe.view.explorer.ExplorerFragment;
import com.thinhlh.mi_recipe.view.my_recipes.MyRecipesFragment;
import com.thinhlh.mi_recipe.view.others.OthersFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeVM> implements HomeUV {

    private final List<Fragment> fragments = new ArrayList<>() {{
        add(new DashboardFragment());
        add(new ExplorerFragment());
        add(new MyRecipesFragment());
        add(new OthersFragment());
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
            int index = 0;
            switch (item.getItemId()) {
                case R.id.home:
                    index = 0;
                    break;
                case R.id.search:
                    index = 1;
                    break;

                case R.id.my_recipes:
                    index = 2;
                    break;

                case R.id.others:
                    index = 3;
                    break;
                default:
                    break;
            }
            for (int i = 0; i < fragments.size(); i++) {
                hideFragment(fragments.get(i));
            }
            showOrAddFragment(binding.fragmentContainerViewTag.getId(), fragments.get(index));

            return true;
        });
    }

    public MyRecipesFragment getMyRecipeFragment() {
        return (MyRecipesFragment) fragments.get(2);
    }
}
