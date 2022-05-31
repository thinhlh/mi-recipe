package com.thinhlh.utils.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public class AppPreferences {

    // Singleton
    private AppPreferences() {
    }

    private static AppPreferences instance;

    public static synchronized AppPreferences get() {
        if (instance == null) {
            instance = new AppPreferences();
        }
        return instance;
    }

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public void init(@NonNull Context context) {

        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean storeValue(@NonNull String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Double) {
            editor.putLong(key, Double.doubleToRawLongBits((Double) value));
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);

        } else if (value instanceof Parcelable) {
            final String stringObject = new Gson().toJson(value);
            editor.putString(key, stringObject);
        } else if (value == null) {
            editor.remove(key);
        }
        return editor.commit();
    }

    /**
     * Get stored string with default value
     * */
    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public String getString(String key) {
        return getString(key, null);
    }

    /**
     * Get stored int with default value
     * */
    public Integer getInteger(String key, Integer defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public Integer getInteger(String key) {
        return getInteger(key, null);
    }

    /**
     * Get stored Long
     * */
    public Long getLong(String key, Long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public Long getLong(String key) {
        return getLong(key, null);
    }

    /**
     * Get stored Float
     * */
    public Float getFloat(String key, Float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    public Float getFloat(String key) {
        return getFloat(key, null);
    }

    /**
     * Get stored Double
     * */
    public Double getDouble(String key, Double defValue) {

        return java.lang.Double.longBitsToDouble(
                getLong(key, java.lang.Double.doubleToLongBits(defValue)
                )
        );
    }

    public Double getDouble(String key) {
        return getDouble(key, null);
    }

    /**
     * Get stored Boolean
     * */
    public Boolean getBoolean(String key, Boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    /**
     * Get stored Parcelable
     * */
    public <T extends Parcelable> T getParcelable(String key, Class<T> type) {
        final String stringObject = getString(key);

        return new Gson().fromJson(stringObject, type);
    }

    /**
     * Clear all excepts keys
     * */
    public void clearAllExcepts(String... keys) {
        for (String key : keys) {
            if (sharedPreferences.contains(key))
                removeObject(key);
        }
    }

    /**
     * Clear all shared preferences
     * */
    public void clearAll() {
        editor.clear().apply();
    }

    /**
     * Delete an object with key
     * */
    public void removeObject(@NonNull String key) {
        editor.remove(key).apply();
    }
}
