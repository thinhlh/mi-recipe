package com.thinhlh.mi_recipe.base.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.divider.MaterialDividerItemDecoration;

public class SpacingItemDecoration extends MaterialDividerItemDecoration {

    public SpacingItemDecoration(@NonNull Context context, int orientation, @DimenRes Integer space) {
        super(context, orientation);
        setUpTransparent(context, space);
        setLastItemDecorated(false);
    }

    public SpacingItemDecoration(@NonNull Context context, @Nullable AttributeSet attrs, int orientation, @DimenRes Integer space) {
        super(context, attrs, orientation);
        setUpTransparent(context, space);
        setLastItemDecorated(true);
    }

    public SpacingItemDecoration(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int orientation, @DimenRes Integer space) {
        super(context, attrs, defStyleAttr, orientation);
        setUpTransparent(context, space);
        setLastItemDecorated(true);
    }

    private void setUpTransparent(@NonNull Context context, @DimenRes Integer space) {
        setDividerThicknessResource(context, space);
        setDividerColor(Color.parseColor("#00000000"));
    }
}
