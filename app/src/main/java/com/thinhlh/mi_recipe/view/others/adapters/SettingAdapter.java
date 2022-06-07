package com.thinhlh.mi_recipe.view.others.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;

public class SettingAdapter extends BaseBindingListAdapter<Setting> {

    private static class DiffCallBack extends DiffUtil.ItemCallback<Setting> {

        @Override
        public boolean areItemsTheSame(@NonNull Setting oldItem, @NonNull Setting newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Setting oldItem, @NonNull Setting newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }

    protected SettingAdapter(@Nullable BaseItemClickListener<Setting> itemClickListener) {
        super(new DiffCallBack(), itemClickListener);
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_setting;
    }
}
