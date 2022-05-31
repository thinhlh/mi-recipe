package com.thinhlh.domain.api.interceptors;

import android.util.Log;

import androidx.annotation.NonNull;

import com.thinhlh.utils.CustomLogger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public class ApiLogger implements HttpLoggingInterceptor.Logger {

    @Override
    public void log(@NonNull String message) {
        if (message.startsWith("{") || message.startsWith("]")) {
            try {
                CustomLogger.json(message);
            } catch (Exception e) {
                Log.w("API exception: ", message);
            }
        } else {
            Log.w("API: ", message);
        }
    }
}
