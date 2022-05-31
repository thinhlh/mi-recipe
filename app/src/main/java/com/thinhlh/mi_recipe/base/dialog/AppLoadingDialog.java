package com.thinhlh.mi_recipe.base.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.databinding.DialogLoadingBinding;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public class AppLoadingDialog extends BaseDialog {

    // Singleton
    private AppLoadingDialog() {
    }

    private static volatile AppLoadingDialog instance;

    public static synchronized AppLoadingDialog get() {
        if (instance == null) {
            instance = new AppLoadingDialog();
        }

        return instance;
    }

    /**
     * Show loading dialog
     */
    public void show() {
        if (context == null) return;
        final DialogLoadingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.dialog_loading,
                null,
                true
        );

        dialog = new AlertDialog.Builder(context, R.style.Base_MaterialAlertDialog)
                .setView(binding.getRoot())
                .setCancelable(true)
                .create();

        dialog.dismiss();

        if (((Activity) context).isFinishing()) return;

        dialog.show();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}
