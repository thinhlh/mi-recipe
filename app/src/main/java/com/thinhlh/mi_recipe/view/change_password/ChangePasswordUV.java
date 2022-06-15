package com.thinhlh.mi_recipe.view.change_password;

import com.thinhlh.mi_recipe.base.userview.BaseUserView;

public interface ChangePasswordUV extends BaseUserView {
    String getCurrentPassword();

    String getNewPassword();

    String getReEnterPassword();

    void signOut();
}
