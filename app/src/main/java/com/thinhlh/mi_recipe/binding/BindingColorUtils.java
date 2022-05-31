package com.thinhlh.mi_recipe.binding;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;


/**
 * Created by thinhlh on 06/03/2022.
 * Copyright (c). All rights reserved
 */
public class BindingColorUtils {
    @BindingAdapter("app:iconTint")
    public static void setTintColor(ImageView view, @ColorRes Integer tint) {
        if (view == null || tint == null) return;
        view.setColorFilter(ContextCompat.getColor(view.getContext(), tint));
    }

    @BindingAdapter("app:backgroundTint")
    public static void setBackgroundTint(View view, @ColorRes Integer backgroundColor) {
        if (view == null || backgroundColor == null) return;
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), backgroundColor));
    }
}
