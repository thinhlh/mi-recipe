package com.thinhlh.mi_recipe.view.login;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

public class LoginVM extends BaseUiViewModel<LoginUV> {

    public final MutableLiveData<Boolean> isLogin = new MutableLiveData<>(true);

    public void login() {
        uiCallback.goToHome();
    }

    public void signUp() {

    }

    public void switchForm() {
        isLogin.postValue(Boolean.FALSE.equals(isLogin.getValue()));
    }
}
