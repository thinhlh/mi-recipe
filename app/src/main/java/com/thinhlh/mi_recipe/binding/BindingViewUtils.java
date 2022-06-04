package com.thinhlh.mi_recipe.binding;


import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

public class BindingViewUtils {
    @BindingAdapter("android:background")
    public static void setBackground(View view, @DrawableRes Integer res) {
        view.setBackground(ContextCompat.getDrawable(view.getContext(), res));
    }
}
