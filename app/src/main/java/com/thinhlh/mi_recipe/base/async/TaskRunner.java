package com.thinhlh.mi_recipe.base.async;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskRunner {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public interface Callback<R> {
        void onComplete(R result);

        void onFailure(Exception e);
    }

    public <R> void executeAsync(Callable<R> callable, Callback<R> callback) {
        executor.execute(() -> {
            try {
                var result = callable.call();

                handler.post(() -> {
                    callback.onComplete(result);
                });
            } catch (Exception e) {
                handler.post(() -> {
                    callback.onFailure(e);
                });
            }

        });
    }
}
