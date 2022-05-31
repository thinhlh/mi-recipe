package com.thinhlh.utils.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public abstract class SystemHelper {
    public static void hideSystemUI(Window window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.getDecorView().setSystemUiVisibility(
                    (View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LOW_PROFILE
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            );
        } else {
            window.setDecorFitsSystemWindows(false);
            if (window.getInsetsController() != null) {

                window.getInsetsController().hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());

                window.getInsetsController().setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        }
    }

    /**
     * Toggle soft input from current context
     */
    public static void toggleSoftInput(Context context, boolean show) {
        final InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (show) {
            inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

            ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    /**
     * Toggle soft input from edit text
     */
    public static void toggleSoftInput(EditText editText, boolean show) {
        final InputMethodManager inputMethodManager = (InputMethodManager) (editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE));

        if (show) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } else {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * Get display size
     */
    public static Point getDisplaySize(DisplayMetrics displayMetrics) {
        final int width = displayMetrics.widthPixels;
        final int height = displayMetrics.heightPixels;

        return new Point(width, height);
    }

    /**
     * Restart the application
     */
    public static <C> void restartApp(@NonNull Context context, Class<C> mainClass) {

        final Intent intent = new Intent(context, mainClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);

        System.exit(0);
    }

}
