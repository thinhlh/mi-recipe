package com.thinhlh.domain.repository.common;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;

/**
 * Created by thinhlh on 3/2/2022.
 * Copyright 2022 (c)
 */
public class CommonRepo extends BaseRepo {
    public void ping(BaseRepoCallback<BaseResponse<String>> callback) {
        commonService.ping().enqueue(getApiCallback(callback));
    }
}
