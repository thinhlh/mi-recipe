package com.thinhlh.mi_recipe.base.viewmodel;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public enum ViewState {

    SHOW_LOADING(-1),
    HIDE_LOADING(-2),
    SHOW_ERROR(-3);

    private final int value;

    public int getValue() {
        return value;
    }

    ViewState(int value) {
        this.value = value;
    }
}
