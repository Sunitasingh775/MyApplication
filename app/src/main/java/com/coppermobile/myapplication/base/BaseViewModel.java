package com.coppermobile.myapplication.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.coppermobile.myapplication.utils.SingleEvent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MediatorLiveData<SingleEvent<StringResException>> errorMessage = new MediatorLiveData<>();

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public LiveData<SingleEvent<StringResException>> getErrorMessage() {
        return errorMessage;
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected void setError(StringResException error) {
        errorMessage.postValue(new SingleEvent<>(error));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public class StringResException extends Exception {

        private final String messageString;

        StringResException(String messageString) {
            this.messageString = messageString;
        }

        public String getMessageString() {
            return messageString;
        }
    }
}
