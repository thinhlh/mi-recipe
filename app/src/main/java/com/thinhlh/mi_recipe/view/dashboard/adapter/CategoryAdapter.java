package com.thinhlh.mi_recipe.view.dashboard.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;

import java.util.Objects;

public class CategoryAdapter extends BaseBindingListAdapter<Category> {

    public CategoryAdapter(@Nullable BaseItemClickListener<Category> itemClickListener) {
        super(new DiffCallback(), itemClickListener);
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<Category> {

        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return Objects.equals(oldItem.getName(), newItem.getName());
        }
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_category;
    }
}
