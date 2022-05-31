package com.thinhlh.mi_recipe.base.viewmodel;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 *
 * This class is used for registering the state of view model, so that the application could react to the current state of view model in the universial way.
 * Which mean, when once any view model state changes, the application automatically change the state.
 */
@MainThread
public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private static String TAG = "SingleLiveEvent";

    private final AtomicBoolean pending = new AtomicBoolean(false);

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        super.observe(owner, t -> {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }

    @Override
    public void setValue(T value) {
        pending.set(true);
        super.setValue(value);
    }
}
