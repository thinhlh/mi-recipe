package com.thinhlh.mi_recipe.view.others.adapters;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingViewHolder;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.ItemGroupSettingBinding;
import com.thinhlh.mi_recipe.databinding.ItemSettingBinding;

import java.util.Objects;
import java.util.Set;

public class SettingGroupAdapter extends BaseBindingListAdapter<SettingGroup> {

    private static class DiffUtilCallback extends DiffUtil.ItemCallback<SettingGroup> {

        @Override
        public boolean areItemsTheSame(@NonNull SettingGroup oldItem, @NonNull SettingGroup newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SettingGroup oldItem, @NonNull SettingGroup newItem) {
            return Objects.equals(oldItem.getTitle(), newItem.getTitle());
        }
    }

    public SettingGroupAdapter(@Nullable BaseItemClickListener<SettingGroup> itemClickListener) {
        super(new DiffUtilCallback(), itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<SettingGroup> holder, int position) {
        super.onBindViewHolder(holder, position);

        var settingAdapter = new SettingAdapter((item, adapterPosition) -> {
            item.getOnClick().run();
        });

        var rv = ((ItemGroupSettingBinding) holder.getBinding()).recyclerView;
        rv.setAdapter(settingAdapter);
        rv.addItemDecoration(new MaterialDividerItemDecoration(holder.itemView.getContext(), MaterialDividerItemDecoration.VERTICAL) {{
            setLastItemDecorated(false);
        }});

        settingAdapter.submitList(getItem(position).getSettings());
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_group_setting;
    }
}
