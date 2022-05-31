package com.thinhlh.domain.api;

import com.thinhlh.utils.constants.Const;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public class ApiConst {

    private static final String BASE_DEV_URL = "https://housing-movie.herokuapp.com";

    private static final String BASE_PROD_URL = "https://housing-movie.herokuapp.com";

    public static final String BASE_URL = Const.DEBUG_MODE ? BASE_DEV_URL : BASE_PROD_URL;

}
