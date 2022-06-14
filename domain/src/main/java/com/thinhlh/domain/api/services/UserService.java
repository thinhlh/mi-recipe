package com.thinhlh.domain.api.services;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.user.UserDetail;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("user/me")
    Call<BaseResponse<UserDetail>> getUserDetail();
}
