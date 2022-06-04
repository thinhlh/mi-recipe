package com.thinhlh.mi_recipe.view.landing;


import androidx.lifecycle.MutableLiveData;

import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

public class LandingVM extends BaseUiViewModel<LandingUV> {
    public final MutableLiveData<Integer> currentPage = new MutableLiveData<>(0);

    public void onButtonClick() {
        uiCallback.onButtonClick();
    }

    public void onSkipClick() {
        uiCallback.goToLoginFragment();
    }
}
