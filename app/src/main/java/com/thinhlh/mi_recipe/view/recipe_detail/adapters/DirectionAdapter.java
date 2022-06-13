package com.thinhlh.mi_recipe.view.recipe_detail.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.domain.repository.recipe.Direction;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;

public class DirectionAdapter extends BaseBindingListAdapter<Direction> {

    private static class DiffUtilCallback extends DiffUtil.ItemCallback<Direction> {

        @Override
        public boolean areItemsTheSame(@NonNull Direction oldItem, @NonNull Direction newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Direction oldItem, @NonNull Direction newItem) {
            return oldItem.getContent().equals(newItem.getContent());
        }
    }

    public DirectionAdapter(@Nullable BaseItemClickListener<Direction> itemClickListener) {
        super(new DiffUtilCallback(), itemClickListener);
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_direction;
    }
}
