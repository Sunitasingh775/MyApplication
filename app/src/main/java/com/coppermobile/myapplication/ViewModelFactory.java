package com.coppermobile.myapplication;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.coppermobile.myapplication.viewmodel.RepoViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ViewModelFactory() {
    }

    private static ViewModelFactory viewModelFactory;

    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            synchronized (ViewModelFactory.class) {
                viewModelFactory = new ViewModelFactory();
            }
        }
        return viewModelFactory;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RepoViewModel.class)) {
            return (T) new RepoViewModel(Injection.getGithubRepository());
        }
        return super.create(modelClass);
    }
}