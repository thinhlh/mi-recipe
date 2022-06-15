package com.thinhlh.mi_recipe.view.change_password;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.user.ChangePasswordRequest;
import com.thinhlh.domain.repository.user.UserDetail;
import com.thinhlh.domain.repository.user.UserRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;

public class ChangePasswordVM extends BaseRepoViewModel<UserRepo, ChangePasswordUV> {
    @Override
    protected UserRepo createRepo() {
        return new UserRepo();
    }

    public void changePassword() {
        var currentPassword = uiCallback.getCurrentPassword();
        var newPassword = uiCallback.getNewPassword();
        var reEnterPassword = uiCallback.getReEnterPassword();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || reEnterPassword.isEmpty()) {
            showErrorMessage("Password cannot be empty");
        } else if (!newPassword.equals(reEnterPassword)) {
            showErrorMessage("Re enter password does not match");
        } else {
            getRepo().changePassword(new ChangePasswordRequest(currentPassword, newPassword), new BaseRepoCallback<BaseResponse<Boolean>>() {

                @Override
                public void apiRequesting(Boolean show) {
                    showLoading(show);
                }

                @Override
                public void apiResponse(BaseResponse<Boolean> data) {
                    if (data.getData()) {
                        uiCallback.signOut();
                    } else {
                        showError("Current password is not correct");
                    }
                }

                @Override
                public void showError(String message) {
                    showErrorMessage(message);
                }
            });

        }
    }

    public void back() {
        uiCallback.onFragmentBackPressed();
    }
}
