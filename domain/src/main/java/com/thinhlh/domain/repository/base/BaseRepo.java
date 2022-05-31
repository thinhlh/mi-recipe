package com.thinhlh.domain.repository.base;

import com.thinhlh.domain.api.base.BaseApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by thinhlh on 3/2/2022.
 * Copyright 2022 (c)
 */
public class BaseRepo extends BaseApi {

    /**
     * This will map the base repo callback to the Retrofit callback to make it
     * eligible for API calling
     * */
    protected <T> Callback<T> getApiCallback(BaseRepoCallback<T> callback) {
        // Show loading
        callback.apiRequesting(true);

        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                callback.apiRequesting(false);
                if (response.isSuccessful()) {
                    callback.apiResponse(getBodyResponse(response));
                    return;
                }
                callback.showError(getErrorMessage(response));
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                // Dismiss loading
                callback.apiRequesting(false);
                callback.apiFailure();
            }
        };
    }


    protected <T> T getBodyResponse(Response<T> response) {
        if (response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

}
