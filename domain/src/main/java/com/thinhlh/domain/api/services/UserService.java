package com.thinhlh.domain.api.services;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.user.ChangePasswordRequest;
import com.thinhlh.domain.repository.user.UserDetail;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @GET("user/me")
    Call<BaseResponse<UserDetail>> getUserDetail();

    @POST("profile/change-password")
    Call<BaseResponse<Boolean>> changePassword(@Body ChangePasswordRequest request);


}
