package com.thinhlh.utils.helper;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public abstract class JsonHelper {
    /**
     * Convert data object to json
     */
    public static <T> String toString(T dataObject) {
        return new Gson().toJson(dataObject, new TypeToken<T>() {
        }.getType());
    }

    /**
     * Convert json to data object
     */
    public static <T> T toObject(String json) {
        return new Gson().fromJson(json, new TypeToken<T>() {
        }.getType());
    }

    /**
     * Read json from assets
     */
    public static String readFromAssets(Context context, String fileName) {
        String json;
        try {
            final InputStream inputStream = context.getAssets().open(fileName);

            final int size = inputStream.available();
            final byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
