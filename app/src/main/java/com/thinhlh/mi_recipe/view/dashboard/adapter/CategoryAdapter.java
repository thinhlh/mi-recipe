package com.thinhlh.mi_recipe.view.dashboard.adapter;

import android.net.Uri;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingViewHolder;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.databinding.ItemCategoryBinding;

import java.util.Objects;

public class CategoryAdapter extends BaseBindingListAdapter<Category> {

    private final @LayoutRes
    Integer categoryLayout;

    public CategoryAdapter(@Nullable BaseItemClickListener<Category> itemClickListener, Integer categoryLayout) {
        super(new DiffCallback(), itemClickListener);
        this.categoryLayout = categoryLayout;
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<Category> {

        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }
    }

    @Override
    protected Integer getViewType(int position) {
        return categoryLayout;
    }
}
