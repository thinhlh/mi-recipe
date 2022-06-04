package com.thinhlh.mi_recipe.base.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public abstract class BaseBindingListAdapter<T> extends ListAdapter<T, BaseBindingViewHolder<T>> {

    private final @Nullable
    BaseItemClickListener<T> itemClickListener;

    protected BaseBindingListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback, @Nullable BaseItemClickListener<T> itemClickListener) {
        super(diffCallback);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BaseBindingViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new BaseBindingViewHolder<>(binding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<T> holder, int position) {
        holder.bindItem(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    protected abstract @LayoutRes
    Integer
    getViewType(int position);
}
