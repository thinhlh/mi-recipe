package com.thinhlh.domain.api.interceptors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public class BearerTokenInterceptor implements Interceptor {

    private final String accessToken;

    private final Runnable timeOut;

    public BearerTokenInterceptor(String accessToken, Runnable timeOut) {
        this.accessToken = accessToken;
        this.timeOut = timeOut;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        final Request.Builder requestBuilder = request.newBuilder();

        if (accessToken != null) {
            requestBuilder.header("Authorization", String.format("Bearer %s", accessToken));
        }

        request = requestBuilder.build();

        try {
            final Response apiResponse = chain.proceed(request);

            if (apiResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // Token expired -> Implement API refresh token
            }

            return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            timeOut.run();
        }

        return chain.proceed(request);
    }
}
