package com.thinhlh.mi_recipe.view.explorer.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import com.thinhlh.mi_recipe.base.pager.FragmentPagerAdapter;

public class ExplorerViewPagerAdapter extends FragmentPagerAdapter {
    public ExplorerViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

}
