package com.thinhlh.mi_recipe.base.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.thinhlh.mi_recipe.base.activity.BaseActivity;
import com.thinhlh.mi_recipe.base.activity.BaseFragmentBindingActivity;
import com.thinhlh.mi_recipe.base.activity.RequestPermissionCallback;
import com.thinhlh.mi_recipe.base.dialog.AlertDialogOnClickListener;
import com.thinhlh.mi_recipe.base.viewmodel.BaseViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.ViewState;

import java.util.List;

/**
 * Created by thinhlh on 06/03/2022.
 * Copyright (c). All rights reserved
 */
public abstract class BaseFragment<T extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    protected Context fragmentContext;

    // Binding
    protected T binding;

    @LayoutRes
    protected abstract Integer layoutRes();

    // View Model
    protected VM viewModel;

    protected abstract Class<VM> viewModelClass();

    protected abstract void initViewModel(VM viewModel);

    // Data & Actions initialization
    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initAction();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentContext = context;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                LayoutInflater.from(fragmentContext),
                layoutRes(),
                container,
                false
        );

        binding.getRoot().setOnTouchListener((View.OnTouchListener) (view, motionEvent) -> {
            view.setClickable(true);
            view.setFocusable(true);
            return false;
        });
        binding.setLifecycleOwner(this);

        // View model

        viewModel = new ViewModelProvider(this).get(viewModelClass());
        initViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initData();
        initAction();

        viewModel.getViewState().observe(getViewLifecycleOwner(), (viewState -> {
            if (viewState.equals(ViewState.SHOW_LOADING)) {
                showLoading(true);

            } else if (viewState.equals(ViewState.HIDE_LOADING)) {
                showLoading(false);

            } else if (viewState.equals(ViewState.SHOW_ERROR)) {
                if (viewModel.errorMessage != null) {
                    showError(viewModel.errorMessage, null, null, null, null, null);
                }
            }
        }));
    }

    public FragmentNavigator getNavigator() {
        return ((BaseFragmentBindingActivity<?, ?>) fragmentContext).getNavigator();
    }

    public void onFragmentBackPressed() {
        getNavigator().goOneBack();
    }

    /**
     * Show loading dialog
     */
    public void showLoading(@NonNull Boolean show) {
        if (this.getActivity() instanceof BaseActivity) {
            ((BaseActivity<?, ?>) this.getActivity()).showLoading(show);
        }
    }

    /**
     * Show with title, message & positive button
     * <p>
     * <p>
     * -----------------------------------------------------
     * | Title                                             |
     * | Message                                           |
     * |                                                   |
     * --------NEGATIVE BUTTON-------POSITIVE BUTTON--------
     */
    public void showError(
            @Nullable String message,
            @Nullable String title,
            @Nullable String positiveText,
            @Nullable String negativeText,
            @Nullable AlertDialogOnClickListener onClickListener,
            @Nullable Boolean reverseLayout
    ) {
        BaseActivity<?, ?> activity = (BaseActivity<?, ?>) getActivity();

        if (activity != null) {
            activity.showAlert(message, title, positiveText, negativeText, onClickListener, reverseLayout);
        }
    }

    /**
     * Get active fragment
     *
     * @return
     */
    protected Fragment getActiveFragment() {
        return getNavigator().getActiveFragment();
    }

    /**
     * Set fragment to a Container view
     *
     * @param containerViewId
     * @param fragment
     */
    protected void setFragmentToContainer(@IdRes int containerViewId, @NonNull Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(containerViewId, fragment).commit();
    }

    /**
     * Check if fragment is already in ContainerView then show fragment
     * else add new one to
     *
     * @param containerViewId
     * @param fragment
     */
    protected void showOrAddFragment(
            @IdRes int containerViewId,
            Fragment fragment
    ) {
        showOrAddFragment(null, containerViewId, fragment);
    }

    /**
     * Check if fragment is already in ContainerView then show fragment
     * else add new one to
     *
     * @param containerViewId
     * @param fragment
     */
    protected void showOrAddFragment(
            FragmentManager fragmentManager,
            @IdRes int containerViewId,
            Fragment fragment
    ) {
        if (fragment == null) return;

        if (fragmentManager == null)
            fragmentManager = getChildFragmentManager();

        String fragmentTag = getFragmentTag(fragment);

        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragmentTag);
        if (fragmentByTag != null) {
            //if the fragment exists, show it.
            fragmentManager.beginTransaction().show(fragmentByTag).commit();
        } else {
            //if the fragment does not exist, add it to fragment manager.
            fragmentManager.beginTransaction().add(containerViewId, fragment, fragmentTag).commit();
        }
    }

    protected void hideFragment(
            Fragment fragment
    ) {
        if (fragment == null) return;

        FragmentManager fragmentManager = getChildFragmentManager();
        String fragmentTag = getFragmentTag(fragment);

        if (fragmentManager.findFragmentByTag(fragmentTag) != null) {
            //if the other fragment is visible, hide it.
            fragmentManager.beginTransaction().hide(fragment).commit();
        }
    }

    protected void removeFragment(
            Fragment fragment
    ) {
        if (fragment == null) return;

        FragmentManager fragmentManager = getChildFragmentManager();
        String fragmentTag = getFragmentTag(fragment);

        if (fragmentManager.findFragmentByTag(fragmentTag) != null) {
            //if the other fragment is visible, remove it.
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    @NonNull
    private String getFragmentTag(@NonNull Fragment fragment) {
        return fragment.getClass().getSimpleName();
    }

    /**
     * Request permission
     */
    public void requestPermission(
            String permissionExplanation,
            List<String> permissions,
            RequestPermissionCallback callback
    ) {
        BaseActivity<?, ?> activity = (BaseActivity<?, ?>) getActivity();

        if (activity != null) {
            activity.requestPermission(permissionExplanation, permissions, callback);
        }
    }
}
