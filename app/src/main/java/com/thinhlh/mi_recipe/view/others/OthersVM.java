package com.thinhlh.mi_recipe.view.others;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.user.UserDetail;
import com.thinhlh.domain.repository.user.UserRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.utils.helper.AppPreferenceKeys;
import com.thinhlh.utils.helper.AppPreferences;

public class OthersVM extends BaseRepoViewModel<UserRepo, OthersUV> {

    public MutableLiveData<Uri> selectedImageUri = new MutableLiveData<>();

    public MutableLiveData<UserDetail> userDetail = new MutableLiveData<>();


    public void signOut() {
        AppPreferences.get().removeObject(AppPreferenceKeys.accessToken);
        uiCallback.goToLandingPage();
    }

    public void getUserDetail() {
        getRepo().getUserDetail(new BaseRepoCallback<>() {
            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<UserDetail> data) {
                userDetail.setValue(data.getData());
            }
        });
    }

    public void showAvatarChosenDialog() {
        uiCallback.showAvatarChosenDialog();
    }

    @Override
    protected UserRepo createRepo() {
        return new UserRepo();
    }
}
