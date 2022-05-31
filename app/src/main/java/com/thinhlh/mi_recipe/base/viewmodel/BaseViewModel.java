package com.thinhlh.mi_recipe.base.viewmodel;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */

// Using lifecycle observer to get the current state of the application
public abstract class BaseViewModel extends ViewModel implements LifecycleObserver {


    // This indicate the current state of view
    private final SingleLiveEvent<ViewState> viewState = new SingleLiveEvent<>();

    public SingleLiveEvent<ViewState> getViewState() {
        return viewState;
    }

    public String errorMessage;

    /**
     * Show or hide loading, called from view model
     */
    public void showLoading(boolean show) {
        viewState.setValue(show ? ViewState.SHOW_LOADING : ViewState.HIDE_LOADING);
    }

    /**
     * Show error
     */
    public void showErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        viewState.setValue(ViewState.SHOW_ERROR);
    }
}
