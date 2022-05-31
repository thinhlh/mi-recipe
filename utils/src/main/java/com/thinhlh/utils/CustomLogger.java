package com.thinhlh.utils;

import com.orhanobut.logger.Logger;
import com.thinhlh.utils.constants.Const;
import com.thinhlh.utils.helper.SystemHelper;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public class CustomLogger {

    private static final boolean showLog = Const.DEBUG_MODE;

    public static void d(String message) {
        if (showLog) {
            Logger.d(message);
        }
    }

    public static void e(String message) {
        if (showLog) {
            Logger.e(message);
        }
    }

    public static void wtf(String message) {
        if (showLog) {
            Logger.wtf(message);
        }
    }

    public static void w(String message) {
        if (showLog) {
            Logger.w(message);
        }
    }

    public static void i(String message) {
        if (showLog) {
            Logger.i(message);
        }
    }

    public static void v(String message) {
        if (showLog) {
            Logger.v(message);
        }
    }

    public static void json(String message) {
        if (showLog) {
            Logger.json(message);
        }
    }

    public static void xml(String message) {
        if (showLog) {
            Logger.xml(message);
        }
    }


}
