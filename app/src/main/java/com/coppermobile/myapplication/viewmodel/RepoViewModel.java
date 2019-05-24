package com.coppermobile.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.coppermobile.myapplication.base.BaseViewModel;
import com.coppermobile.myapplication.network.models.response.SearchResponse;
import com.coppermobile.myapplication.repository.GithubRepository;
import com.coppermobile.myapplication.utils.EspressoIdlingResource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepoViewModel extends BaseViewModel {

    private GithubRepository githubRepository;
    private MutableLiveData<SearchResponse> mutableLiveData = new MutableLiveData<>();
    public final ObservableInt count = new ObservableInt();
    public final ObservableInt visibility = new ObservableInt(View.GONE);

    public RepoViewModel(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public void searchRepos(@NonNull String searchedText) {

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice
        visibility.set(View.VISIBLE);

        getCompositeDisposable().add(githubRepository.searchRepos(searchedText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResponse -> {
                    // now that the data has been loaded, we can mark the app as idle
                    // first, make sure the app is still marked as busy then decrement, there might be cases
                    // when other components finished their asynchronous tasks and marked the app as idle
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }

                    mutableLiveData.setValue(searchResponse);
                    count.set(searchResponse.getTotalCount());
                    visibility.set(View.GONE);
                }, throwable -> {
                    setError((StringResException) throwable);
                    visibility.set(View.GONE);
                }));
    }

    public LiveData<SearchResponse> getSearchResponse() {
        return mutableLiveData;
    }
}
