package com.thinhlh.domain.repository.auth;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.auth.login.LoginRequest;
import com.thinhlh.domain.repository.auth.login.Tokens;
import com.thinhlh.domain.repository.auth.register.RegisterRequest;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;

public class AuthRepo extends BaseRepo {
    public void login(
            LoginRequest body,
            BaseRepoCallback<BaseResponse<Tokens>> callback
    ) {
        authService.login(body).enqueue(getApiCallback(callback));
    }

    public void register(
            RegisterRequest body,
            BaseRepoCallback<BaseResponse<Boolean>> callback
    ) {
        authService.register(body).enqueue(getApiCallback(callback));
    }
}
