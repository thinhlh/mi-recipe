package com.thinhlh.domain.api.base;

import com.thinhlh.domain.api.RetrofitService;
import com.thinhlh.domain.api.services.CommonService;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 * This is where we declare all services
 */
public abstract class BaseApi extends BaseApiError {
    protected CommonService commonService = RetrofitService.get().createService(CommonService.class);
}
