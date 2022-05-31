package com.thinhlh.mi_recipe.base.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
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
