package com.thinhlh.domain.repository.user;

import com.google.gson.annotations.SerializedName;
import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;


public class UserRepo extends BaseRepo {
    public void getUserDetail(BaseRepoCallback<BaseResponse<UserDetail>> callback) {
        userService.getUserDetail().enqueue(getApiCallback(callback));
    }
}
