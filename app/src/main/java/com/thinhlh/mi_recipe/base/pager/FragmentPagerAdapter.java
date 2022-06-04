package com.thinhlh.mi_recipe.base.pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public class FragmentPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public FragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    /**
     * Add new fragment to the pager
     */
    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    /**
     * Update the fragment at specific position
     */
    public void updateFragment(int index, Fragment fragment) {
        if (index < 0 || index >= getItemCount()) return;
        fragmentList.set(index, fragment);
    }

    /**
     * Remove the fragment at specific position
     */
    public void removeFragment(int position) {
        if (position < 0 || position >= getItemCount()) return;
        fragmentList.remove(position);
    }

    /**
     * Get the fragment at specific position
     */
    public Fragment getFragment(int position) {
        if (position < 0 || position >= getItemCount()) return null;
        return fragmentList.get(position);
    }
}
