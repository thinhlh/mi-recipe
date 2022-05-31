package com.thinhlh.domain.repository.base;

/**
 * Created by thinhlh on 3/2/2022.
 * Copyright 2022 (c)
 */
public interface BaseRepoCallback<T> {
    /**
     * Called while requesting api, default is show app loading
     */
    default void apiRequesting(Boolean showLoading) {

    }

    /**
     * Called when api return success but the body return failure
     */
    default void showError(String message) {

    }

    /**
     * Called when api return success is true
     */
    void apiResponse(T data);

    /**
     * Called when api calling fail
     */
    default void apiFailure() {
    }
}
