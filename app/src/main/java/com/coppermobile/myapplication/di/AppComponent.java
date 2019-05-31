package com.coppermobile.myapplication.di;

import com.coppermobile.myapplication.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules =
        {AndroidSupportInjectionModule.class,
                NetModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class MyBuilder extends AndroidInjector.Builder<BaseApplication> {
    }
}
