package com.thinhlh.domain.api.services;

import com.thinhlh.domain.api.base.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by thinhlh on 3/2/2022.
 * Copyright 2022 (c)
 */
public interface CommonService {

    @GET("/ping")
    Call<BaseResponse<String>> ping();
}
