package com.thinhlh.mi_recipe.view.recipe_detail.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;

import java.util.Objects;

public class IngredientAdapter extends BaseBindingListAdapter<Ingredient> {

    private static class DiffUtilCallback extends DiffUtil.ItemCallback<Ingredient> {

        @Override
        public boolean areItemsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
            return Objects.equals(oldItem.getTitle(), newItem.getTitle());
        }
    }

    public IngredientAdapter(@Nullable BaseItemClickListener<Ingredient> itemClickListener) {
        super(new DiffUtilCallback(), itemClickListener);
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_ingredient;
    }
}
