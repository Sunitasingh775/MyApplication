package com.coppermobile.myapplication.base;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends BaseApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}