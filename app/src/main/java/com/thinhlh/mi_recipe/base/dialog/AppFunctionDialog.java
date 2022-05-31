package com.thinhlh.mi_recipe.base.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ViewDataBinding;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.thinhlh.mi_recipe.R;

/**
 * Created by thinhlh on 04/03/2022.
 * Copyright (c). All rights reserved
 * <p>
 * Custom dialog
 */
public class AppFunctionDialog extends BaseDialog {
    private AppFunctionDialog() {

    }

    private static volatile AppFunctionDialog instance;

    public static synchronized AppFunctionDialog get() {
        if (instance == null) {
            instance = new AppFunctionDialog();
        }

        return instance;
    }

    /**
     * Show custom layout
     *
     * @param layoutBinding is the ViewBinding of the layout
     * @param maxWidth:     Max width of the dialog scaled by the width of device
     * @param maxHeight:    Max height of the dialog scaled by the height of device
     */

    public AlertDialog showCustomLayout(
            @NonNull ViewDataBinding layoutBinding,
            Double maxWidth,
            Double maxHeight
    ) {
        if (context == null) return null;

        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.Base_MaterialAlertDialog);

        builder
                .setCancelable(true)
                .setView(layoutBinding.getRoot());

        // ### Setup dialog layout
        final DisplayMetrics metrics = new DisplayMetrics();

        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        windowManager.getDefaultDisplay().getMetrics(metrics);

        final int maxDialogHeight = (int) (metrics.heightPixels * maxHeight);

        // Setup dialog params
        final ViewGroup.LayoutParams dialogParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (layoutBinding.getRoot().getMeasuredHeight() >= maxDialogHeight) {
            dialogParams.height = maxDialogHeight;
        }

        dialogParams.width = (int) (metrics.widthPixels * maxWidth);

        // ### End setup dialog layout

        // Create Dialog
        if (dialog.isShowing()) dialog.dismiss();

        final AlertDialog customDialog = builder.show();
        customDialog.getWindow().setLayout(dialogParams.width, dialogParams.height);

        // Custom background
        final Window window = customDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return customDialog;
    }

    public AlertDialog showCustomLayoutMaxWidth(
            @NonNull ViewDataBinding layoutBinding,
            Double maxWidth
    ) {
        return showCustomLayout(layoutBinding, maxWidth, 1.0);
    }

    public AlertDialog showCustomLayoutMaxHeight(
            @NonNull ViewDataBinding layoutBinding,
            Double maxHeight
    ) {
        return showCustomLayout(layoutBinding, 0.5, maxHeight);
    }

    public AlertDialog showCustomLayoutDefault(
            @NonNull ViewDataBinding layoutBinding
    ) {
        return showCustomLayout(layoutBinding, 0.5, 1.0);
    }

    private void setUpDialogLayout(ViewDataBinding layoutBinding, Double maxWidth, Double maxHeight) {


    }
}
