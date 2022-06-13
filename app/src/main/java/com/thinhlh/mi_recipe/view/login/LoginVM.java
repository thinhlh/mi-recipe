package com.thinhlh.mi_recipe.view.login;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.domain.api.RetrofitService;
import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.auth.AuthRepo;
import com.thinhlh.domain.repository.auth.login.Tokens;
import com.thinhlh.domain.repository.auth.login.LoginRequest;
import com.thinhlh.domain.repository.auth.register.RegisterRequest;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.utils.helper.AppPreferenceKeys;
import com.thinhlh.utils.helper.AppPreferences;
import com.thinhlh.utils.helper.SystemHelper;

public class LoginVM extends BaseRepoViewModel<AuthRepo, LoginUV> {

    public final MutableLiveData<Boolean> isLogin = new MutableLiveData<>(true);
    public final MutableLiveData<Boolean> passwordShow = new MutableLiveData<>(false);
    public final MutableLiveData<Boolean> confirmPasswordShow = new MutableLiveData<>(false);

    public void login() {
        String email = uiCallback.getEmailField();
        String password = uiCallback.getPasswordField();
        getRepo().login(new LoginRequest(email, password), new BaseRepoCallback<BaseResponse<Tokens>>() {

            @Override
            public void apiRequesting(Boolean show) {
                uiCallback.hideKeyboard();
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<Tokens> data) {
                var accessToken = data.getData().getAccessToken();
                AppPreferences.get().storeValue(AppPreferenceKeys.accessToken, accessToken);
                RetrofitService.get().init(accessToken, null);

                uiCallback.goToHome();
            }

            @Override
            public void apiFailure() {
            }

            @Override
            public void showError(String message) {
                showErrorMessage(message);
            }
        });
    }

    public void signUp() {
        String email = uiCallback.getEmailField();
        String password = uiCallback.getPasswordField();
        String confirmPassword = uiCallback.getConfirmPasswordField();
        String name = uiCallback.getNameField();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            showErrorMessage("None of those fields can be empty!");
        } else if (!password.equals(confirmPassword)) {
            showErrorMessage("Password and Confirmation must match!");
        } else {
            getRepo().register(new RegisterRequest(
                    email,
                    name,
                    password
            ), new BaseRepoCallback<>() {
                @Override
                public void apiRequesting(Boolean show) {
                    showLoading(show);
                }

                @Override
                public void apiResponse(BaseResponse<Boolean> data) {
                    isLogin.postValue(true);
                }

                @Override
                public void showError(String message) {
                    showErrorMessage(message);
                }
            });
        }

    }

    public void changePasswordVisibility() {
        passwordShow.postValue(Boolean.FALSE.equals(passwordShow.getValue()));
    }

    public void changePasswordConfirmationVisibility() {
        confirmPasswordShow.postValue(Boolean.FALSE.equals(confirmPasswordShow.getValue()));
    }

    public void switchForm() {
        isLogin.postValue(Boolean.FALSE.equals(isLogin.getValue()));
    }

    @Override
    protected AuthRepo createRepo() {
        return new AuthRepo();
    }
}
