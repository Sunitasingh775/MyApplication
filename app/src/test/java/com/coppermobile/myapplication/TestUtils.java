package com.coppermobile.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.annotation.NonNull;

public abstract class TestUtils {

    public static LifecycleOwner TEST_OBSERVER = new LifecycleOwner() {

        LifecycleRegistry mRegistry = init();

        // Creates a LifecycleRegistry in RESUMED state.
        private LifecycleRegistry init() {
            LifecycleRegistry registry = new LifecycleRegistry(this);
            registry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
            registry.handleLifecycleEvent(Lifecycle.Event.ON_START);
            registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
            return registry;
        }

        @NonNull
        @Override
        public Lifecycle getLifecycle() {
            return mRegistry;
        }
    };
}
