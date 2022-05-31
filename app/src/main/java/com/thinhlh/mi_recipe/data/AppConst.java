package com.thinhlh.mi_recipe.data;

/**
 * Created by thinhlh on 06/03/2022.
 * Copyright (c). All rights reserved
 */
public abstract class AppConst {
    public static boolean RUN_IN_FULLSCREEN = true;

    public abstract static class LOCATION {
        public static Long REQUEST_LOCATION_MIN_TIME = 500L;
        public static Float REQUEST_LOCATION_MIN_DISTANCE = 50f;
    }

    public abstract static class DATETIME {
        public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }
}
