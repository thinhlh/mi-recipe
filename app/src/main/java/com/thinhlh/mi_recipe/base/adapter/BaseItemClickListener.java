package com.thinhlh.mi_recipe.base.adapter;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public interface BaseItemClickListener<T> {
    void onItemClick(T item, Integer adapterPosition);
}
