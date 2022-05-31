package com.thinhlh.mi_recipe.base.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.databinding.DialogMessageBinding;


/**
 * Created by thinhlh on 04/03/2022.
 * Copyright (c). All rights reserved
 */
public class AppAlertDialog extends BaseDialog {

    private AppAlertDialog() {
    }

    private static volatile AppAlertDialog instance;

    public static synchronized AppAlertDialog get() {
        if (instance == null) {
            instance = new AppAlertDialog();
        }

        return instance;
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
    public void show(
            @NonNull String message,
            @Nullable String title,
            @Nullable String positiveText,
            @Nullable String negativeText,
            @Nullable AlertDialogOnClickListener onClickListener,
            @Nullable Boolean reverseLayout
    ) {
        if (context != null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);

            final DialogMessageBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.dialog_message,
                    null, true
            );

            builder
                    .setCancelable(false)
                    .setView(binding.getRoot());

            // Setup binding values
            binding.setTitle(title != null ? title : context.getString(R.string.error));
            binding.setMessage(message);
            binding.setReverseLayout(reverseLayout);

            // Confirm button
            binding.setBtnConfirmText(positiveText != null ? positiveText : context.getString(R.string.ok));
            binding.setBtnConfirmOnClick(view -> {
                dismiss();
                if (onClickListener != null) {
                    onClickListener.onPositiveClick();
                }
            });

            // Denied button
            binding.setBtnDeniedText(negativeText);
            binding.setBtnDeniedOnClick(view -> {
                dismiss();
                if (onClickListener != null) {
                    onClickListener.onNegativeClick();
                }
            });

            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dismiss();

            if (((Activity) context).isFinishing()) return;
            dialog.show();

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    public void show(
            @NonNull String message,
            @Nullable String title,
            @Nullable String confirmText,
            @Nullable Runnable confirmClick
    ) {
        this.show(message, title, confirmText, "", new AlertDialogOnClickListener() {
            @Override
            public void onPositiveClick() {
                if (confirmClick != null) {
                    confirmClick.run();
                }
            }

            @Override
            public void onNegativeClick() {

            }
        }, false);
    }

}
