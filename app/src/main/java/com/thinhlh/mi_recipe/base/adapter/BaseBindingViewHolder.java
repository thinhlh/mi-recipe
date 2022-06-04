package com.thinhlh.mi_recipe.base.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.thinhlh.mi_recipe.BR;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
class BaseBindingViewHolder<T> extends RecyclerView.ViewHolder {

    private T item;
    private final ViewDataBinding binding;
    private @Nullable
    BaseItemClickListener<T> onItemClick;

    public BaseBindingViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public BaseBindingViewHolder(@NonNull ViewDataBinding binding, @Nullable BaseItemClickListener<T> onItemClick) {
        this(binding);
        this.onItemClick = onItemClick;
    }

    public void bindItem(T item) {
        if (item != null) {
            this.item = item;
            binding.setVariable(BR.item, item);
            binding.executePendingBindings();
            initAction();
        }
    }

    private void initAction() {
        binding.getRoot().setOnClickListener((v) -> {
            if (item != null && onItemClick != null) {
                onItemClick.onItemClick(item, getAdapterPosition());
            }
        });
    }
}
