package com.thinhlh.domain.api;

import com.thinhlh.domain.api.interceptors.ApiLogger;
import com.thinhlh.domain.api.interceptors.BearerTokenInterceptor;
import com.thinhlh.utils.constants.Const;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public final class RetrofitService {

    // Singleton
    private RetrofitService() {

    }

    private static volatile RetrofitService instance;

    public static synchronized RetrofitService get() {

        if (instance == null) {
            final RetrofitService newInstance = new RetrofitService();
            instance = newInstance;
            return newInstance;
        } else {
            return instance;
        }
    }


    // Local variables
    private Runnable timeOut;

    private final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new ApiLogger()).setLevel(
            Const.DEBUG_MODE ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE
    );

    private BearerTokenInterceptor bearerTokenInterceptor;

    private final int REQ_TIME_OUT = 60;

    public void init(String accessToken, Runnable timeOut) {
        if (timeOut != null) {
            this.timeOut = timeOut;
        }
        bearerTokenInterceptor = new BearerTokenInterceptor(accessToken, this.timeOut);
    }


    /**
     * Create API builder
     * */
    private Retrofit createBuilder() {
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .readTimeout(REQ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(REQ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(REQ_TIME_OUT, TimeUnit.SECONDS)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addInterceptor(loggingInterceptor);

        if (bearerTokenInterceptor != null) {
            okHttpBuilder.addInterceptor(bearerTokenInterceptor);
        }

        return new Retrofit.Builder()
                .baseUrl(ApiConst.BASE_URL)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * Create new API service
     * */
    public <T> T createService(Class<T> serviceClazz) {
        return createBuilder().create(serviceClazz);
    }
}
