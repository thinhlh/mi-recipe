package com.thinhlh.mi_recipe.base.fragment;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;


import com.thinhlh.mi_recipe.R;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by thinhlh on 04/03/2022.
 * Copyright (c). All rights reserved
 */
public class FragmentNavigator {
    @NonNull
    private final FragmentManager mFragmentManager;

    @IdRes
    private final int mDefaultContainer;

    private @Nullable
    OnFragmentStackChanged onStackChanged;

    public void setOnStackChanged(@Nullable OnFragmentStackChanged onStackChanged) {
        this.onStackChanged = onStackChanged;
    }

    /**
     * This constructor should be only called once per activity
     *
     * @param fragmentManager  Your FragmentManger
     * @param defaultContainer Your container id where the fragments should be placed
     */
    public FragmentNavigator(
            @NonNull
                    FragmentManager fragmentManager, Integer defaultContainer) {
        this.mFragmentManager = fragmentManager;
        this.mDefaultContainer = defaultContainer;

        mFragmentManager.addOnBackStackChangedListener(() -> {
            if (getActiveFragment() != null) {
                if (onStackChanged != null) {
                    onStackChanged.onChanged(getActiveFragment());
                }
            }

        });
    }

    @Nullable
    private OnFragmentStackChanged onFragmentStackChanged;
    @Nullable
    private String mRootFragmentTag;
    @Nullable
    private Lifecycle.State currentState;

    public void setOnFragmentStackChanged(@Nullable OnFragmentStackChanged onFragmentStackChanged) {
        this.onFragmentStackChanged = onFragmentStackChanged;
    }

    /**
     * @return the current active fragment. If no fragment is active it return null.
     */
    @Nullable
    public Fragment getActiveFragment() {

        @Nullable final String tag =
                mFragmentManager.getBackStackEntryCount() == 0
                        ? mRootFragmentTag
                        : mFragmentManager.getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1).getName();

        return mFragmentManager.findFragmentByTag(tag);
    }

    /**
     * Get previous fragment on back stack
     *
     * @return Fragment
     */
    @Nullable
    public Fragment getPreviousFragment() {
        final int count = mFragmentManager.getFragments().size();

        return count > 0 ?
                mFragmentManager.getFragments().get((count - 1))
                : null;
    }

    /**
     * Pushes the fragment, and add it to the history (BackStack) if you have set a default animation
     * it will be added to the transaction.
     *
     * @param fragment  The fragment which will be added
     * @param direction indication the transition direction
     */

    public void goTo(Fragment fragment, @Nullable TransactionDirection direction) {
        currentState = fragment.getLifecycle().getCurrentState();

        final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (direction != null) {
            switch (direction) {
                case RTL:
                    fragmentTransaction.setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right
                    );
                    break;
                case LTR:
                    fragmentTransaction.setCustomAnimations(
                            R.anim.enter_from_left,
                            R.anim.exit_to_right,
                            R.anim.enter_from_right,
                            R.anim.exit_to_left
                    );
                    break;
                case BOTTOM_UP:
                    fragmentTransaction.setCustomAnimations(
                            R.anim.enter_from_bottom,
                            R.anim.exit_to_top,
                            R.anim.enter_from_top,
                            R.anim.exit_to_bottom
                    );
                    break;
                case TOP_DOWN:
                    fragmentTransaction.setCustomAnimations(
                            R.anim.enter_from_top,
                            R.anim.exit_to_bottom,
                            R.anim.enter_from_bottom,
                            R.anim.exit_to_top
                    );
                    break;
            }
        }

        fragmentTransaction.addToBackStack(getTag(fragment));
        fragmentTransaction.add(mDefaultContainer, fragment, getTag(fragment));

        if (currentState != null) {
            if (currentState.isAtLeast(Lifecycle.State.CREATED)) {
                fragmentTransaction.setMaxLifecycle(fragment, currentState);
            }
            fragmentTransaction.commit();
        }
        mFragmentManager.executePendingTransactions();
    }

    public void goTo(Fragment fragment) {
        goTo(fragment, null);
    }

    /**
     * This is just a helper method which returns the simple name of the fragment.
     *
     * @param fragment That get added to the history (BackStack)
     * @return The simple name of the given fragment
     */
    private String getTag(@Nullable Fragment fragment) {
        return fragment != null ? fragment.getClass().getSimpleName() : "";
    }

    private @Nullable
    Fragment rootFragment;

    /**
     * Get root fragment
     *
     * @return root fragment
     */
    @Nullable
    public Fragment getRootFragment() {
        return mFragmentManager.findFragmentByTag(mRootFragmentTag);
    }

    /**
     * Set the new root fragment. If there is any entry in the history (BackStack) it will be
     * deleted.
     *
     * @param rootFragment The new root fragment
     */
    public void setRootFragment(@Nullable Fragment rootFragment) {
        if (rootFragment == null) return;

        if (size() > 0) clearHistory();
        mRootFragmentTag = getTag(rootFragment);
        replaceCurrentFragment(rootFragment);

    }

    /**
     * Replace the current fragment with the given one, without to add it to backstack. So when the
     * users navigates away from the given fragment it will not appear in the history.
     *
     * @param fragment The new fragment
     */
    private void replaceCurrentFragment(Fragment fragment) {
        currentState = fragment.getLifecycle().getCurrentState();

        final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        fragmentTransaction.replace(mDefaultContainer, fragment, getTag(fragment));
        if (currentState.isAtLeast(Lifecycle.State.CREATED)) {
            fragmentTransaction.setMaxLifecycle(fragment, currentState);
        }
        fragmentTransaction.commitAllowingStateLoss();

        mFragmentManager.executePendingTransactions();
    }

    /**
     * Goes one entry back in the history
     */
    public void goOneBack() {
        mFragmentManager.popBackStackImmediate();
    }

    /**
     * Goes one entry back in the history
     */
    public void goOneBackTo(String tagFragment) {
        int i = size() - 1;
        while (size() >= 1) {
            if (Objects.requireNonNull(mFragmentManager.getBackStackEntryAt(i).getName()) != tagFragment) {
                goOneBack();
                i--;
            } else {
                return;
            }
        }
    }

    /**
     * @return The current size of the history (BackStack)
     */
    public int size() {
        return mFragmentManager.getBackStackEntryCount();
    }

    /**
     * Goes the whole history back until to the first fragment in the history. It would be the same if
     * the user would click so many times the back button until he reach the first fragment of the
     * app.
     */
    public void goToRoot() {
        while (size() >= 1) {
            goOneBack();
        }
    }

    /**
     * Go to roor fragment with the listener when they current fragment is root fragment
     */
    public void goToRoot(Consumer<Fragment> listener) {
        while (size() >= 1) {
            if (size() <= 1) {
                listener.accept(rootFragment);
                break;
            }
            goOneBack();
        }
    }

    public void clearHistory() {
        while (mFragmentManager.popBackStackImmediate()) ;
    }

    /**
     * Set the fragment result, used when passing data betweens fragment from children to parent
     * For passing data from parent -> children consider using newInstance instead.
     */
    public void setFragmentResult(String requestKey, Bundle result) {
        mFragmentManager.setFragmentResult(requestKey, result);
    }

    /**
     * Set the listener result when children passing the data to parent
     * This is usually called inside parent fragment while set fragment result usually called inside children
     * When navigating back from children, this listener will be executed
     */
    public void setFragmentResultListener(String requestKey, LifecycleOwner lifecycleOwner, FragmentResultListener listener) {
        mFragmentManager.setFragmentResultListener(requestKey, lifecycleOwner, listener);
    }
}


