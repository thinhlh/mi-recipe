package com.thinhlh.mi_recipe.view.dashboard.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;

import java.util.Objects;

public class RecipeAdapter extends BaseBindingListAdapter<Recipe> {
    private final @LayoutRes
    Integer recipeLayout;

    private static class DiffCallback extends DiffUtil.ItemCallback<Recipe> {

        @Override
        public boolean areItemsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
            return Objects.equals(oldItem.getTitle(), newItem.getTitle());
        }
    }

    public RecipeAdapter(@Nullable BaseItemClickListener<Recipe> itemClickListener, Integer recipeLayout) {
        super(new DiffCallback(), itemClickListener);
        this.recipeLayout = recipeLayout;
    }

    @Override
    protected Integer getViewType(int position) {
        return recipeLayout;
    }
}
