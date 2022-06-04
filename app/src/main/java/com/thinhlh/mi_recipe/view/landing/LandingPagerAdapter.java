package com.thinhlh.mi_recipe.view.landing;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;

import java.util.Objects;

class LandingPagerAdapter extends BaseBindingListAdapter<LandingItem> {

    private static class DiffCallback extends DiffUtil.ItemCallback<LandingItem> {

        @Override
        public boolean areItemsTheSame(@NonNull LandingItem oldItem, @NonNull LandingItem newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull LandingItem oldItem, @NonNull LandingItem newItem) {
            return Objects.equals(oldItem.getImage(), newItem.getImage());
        }
    }

    protected LandingPagerAdapter(@Nullable BaseItemClickListener<LandingItem> itemClickListener) {
        super(new DiffCallback(), itemClickListener);
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_landing;
    }
}
