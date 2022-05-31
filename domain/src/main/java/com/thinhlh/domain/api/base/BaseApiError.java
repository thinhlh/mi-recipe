package com.thinhlh.domain.api.base;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
abstract class BaseApiError {
    public <T> String getErrorMessage(Response<T> apiResponse) {
        final StringBuilder stringBuilder = new StringBuilder();

        if (!apiResponse.isSuccessful()) {
            final ResponseBody errorBody = apiResponse.errorBody();

            try {
                final String responseString = errorBody != null ? errorBody.string() : "";

                if (responseString.startsWith("{") || responseString.startsWith("]")) {
                    final BaseResponse<String> error = new Gson().fromJson(responseString, new TypeToken<BaseResponse<T>>() {
                    }.getType());

                    stringBuilder.append(error.data != null ? error.data : error.message);
                } else {
                    stringBuilder.append(responseString);
                }

            } catch (IOException e) {
                stringBuilder.append(e.getMessage());
            }

        }

        return stringBuilder.toString();
    }
}