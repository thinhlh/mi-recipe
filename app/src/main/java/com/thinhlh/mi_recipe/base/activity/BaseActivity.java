package com.thinhlh.mi_recipe.base.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.dialog.AlertDialogOnClickListener;
import com.thinhlh.mi_recipe.base.dialog.AppAlertDialog;
import com.thinhlh.mi_recipe.base.dialog.AppFunctionDialog;
import com.thinhlh.mi_recipe.base.dialog.AppLoadingDialog;
import com.thinhlh.mi_recipe.base.viewmodel.BaseViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.ViewState;

import java.util.List;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public abstract class BaseActivity<T extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements LifecycleObserver {

    protected Context context;

    protected T binding;

    @LayoutRes
    protected abstract Integer layoutRes();

    // Logic functions

    /**
     * Setup the properties of view
     */
    protected abstract void initView();

    /**
     * Setup data binding values, view model values...
     */
    protected abstract void initData();

    /**
     * Setup listener...
     */
    protected abstract void initActions();

    // View model setup
    protected VM viewModel;

    protected abstract Class<VM> viewModelClass();

    protected abstract void initViewModel(VM viewModel);

    // Dialog

    // Keyboard
    public void onKeyboardVisibilityListener(boolean isShow) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        // Add Lifecycle observer to the activity
        getLifecycle().addObserver(this);

        // DataBinding setup
        binding = DataBindingUtil.setContentView(this, layoutRes());
        binding.setLifecycleOwner(this);

        // View Model setup
        viewModel = new ViewModelProvider(this).get(viewModelClass());

        // Run the initialization of view model then observe the view state
        initViewModel(viewModel);

        viewModel.getViewState().observe(this, viewState -> {

            if (viewState.equals(ViewState.SHOW_LOADING)) {
                showLoading(true);

            } else if (viewState.equals(ViewState.HIDE_LOADING)) {
                showLoading(false);

            } else if (viewState.equals(ViewState.SHOW_ERROR)) {
                if (viewModel.errorMessage != null) {
                    showError(viewModel.errorMessage, null);
                }
            }
        });

        // Run initializations
        initView();
        initData();
        initActions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initAppDialogs();
    }

    private void initAppDialogs() {
        AppAlertDialog.get().init(this);
        AppFunctionDialog.get().init(this);
        AppLoadingDialog.get().init(this);
    }

    /**
     * Hide keyboard when click outside EditText
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            try {
                final View currentFocus = getCurrentFocus();

                if (currentFocus instanceof EditText) {
                    final Rect outRect = new Rect();

                    currentFocus.getGlobalVisibleRect(outRect);
                }
            } catch (Exception e) {

            }
        }

        return super.dispatchTouchEvent(ev);
    }

    public void showLoading(@NonNull Boolean show) {
        dismissLoading();

        if (show) {
            new Handler().postDelayed(() -> {
                AppLoadingDialog.get().show();
            }, 100L);
        }
    }

    public void showError(String message, @Nullable Runnable onClickListener) {
        AppAlertDialog.get().show(message, context.getString(R.string.error), context.getString(R.string.ok), onClickListener);
    }

    public void showAlert(
            String message,
            @Nullable String title,
            @Nullable String positiveText,
            @Nullable String negativeText,
            @Nullable AlertDialogOnClickListener onClickListener,
            @Nullable Boolean reverseLayout
    ) {
        AppAlertDialog.get().show(message, title, positiveText, negativeText, onClickListener, reverseLayout);
    }

    private void dismissLoading() {
        AppLoadingDialog.get().dismiss();
        AppAlertDialog.get().dismiss();
    }

    public void requestPermission(
            String permissionExplanation,
            List<String> permissions,
            RequestPermissionCallback callback
    ) {
        Dexter.withContext(this).withPermissions(permissions).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    callback.onPermissionGranted();
                } else {
                    callback.onPermissionDenied();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                if (permissionToken != null) {
                    permissionToken.continuePermissionRequest();
                }
            }
        });
    }

    public void goToAppSettings() {
        final Uri uri = Uri.fromParts("package", getPackageName(), null);
        final Intent intent = new Intent((Settings.ACTION_APPLICATION_DETAILS_SETTINGS));
        intent.setData(uri);

        startActivity(intent);
    }
}
