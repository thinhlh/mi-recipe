package com.thinhlh.mi_recipe.binding;


import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;
import com.thinhlh.mi_recipe.R;

public class BindingImageUtils {
    @BindingAdapter("android:src")
    public static void loadImageFromUrl(ImageView imageView, String src) {
        if (src == null) return;
        var context = imageView.getContext();
        //Unable to use placeholder here as it wont show image
        Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions())
                .load(src)
                .placeholder(startLoadingProgress(context))
                .thumbnail(0.5f)
                .error(R.drawable.landing_3)
                .centerCrop()
                .into(imageView);
    }

    private static CircularProgressDrawable startLoadingProgress(Context context) {
        var circularProgress = new CircularProgressDrawable(context);

        circularProgress.setStrokeWidth(4f);
        circularProgress.setCenterRadius(30f);
        circularProgress.setColorSchemeColors(ContextCompat.getColor(context, R.color.secondary));
        circularProgress.start();

        return circularProgress;
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}


