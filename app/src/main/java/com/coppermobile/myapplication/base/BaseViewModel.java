package com.coppermobile.myapplication.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.coppermobile.myapplication.utils.SingleEvent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MediatorLiveData<SingleEvent<StringResEx>> errorMessage = new MediatorLiveData<>();

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public LiveData<SingleEvent<StringResEx>> getErrorMessage() {
        return errorMessage;
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected void setError(StringResEx error) {
        errorMessage.postValue(new SingleEvent<>(error));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public class StringResEx extends Throwable {

        private final String messageString;

        StringResEx(String messageString) {
            this.messageString = messageString;
        }

        public String getMessageString() {
            return messageString;
        }
    }
}
