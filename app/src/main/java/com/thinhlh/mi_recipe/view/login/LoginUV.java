package com.thinhlh.mi_recipe.view.login;


import com.thinhlh.mi_recipe.base.userview.BaseUserView;

public interface LoginUV extends BaseUserView {
    void goToHome();
    void hideKeyboard();

    String getEmailField();

    String getPasswordField();

    String getConfirmPasswordField();

    String getNameField();
}
