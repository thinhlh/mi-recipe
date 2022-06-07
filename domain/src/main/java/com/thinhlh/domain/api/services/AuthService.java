package com.thinhlh.domain.api.services;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.auth.login.LoginRequest;
import com.thinhlh.domain.repository.auth.login.Tokens;
import com.thinhlh.domain.repository.auth.register.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("login")
    Call<BaseResponse<Tokens>> login(@Body LoginRequest body);

    @POST("register")
    Call<BaseResponse<Boolean>> register(@Body RegisterRequest body);
}
