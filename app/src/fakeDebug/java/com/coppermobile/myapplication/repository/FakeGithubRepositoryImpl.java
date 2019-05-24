package com.coppermobile.myapplication.repository;

import android.support.annotation.NonNull;

import com.coppermobile.myapplication.network.models.response.RepoNameResponse;
import com.coppermobile.myapplication.network.models.response.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FakeGithubRepositoryImpl implements GithubRepository {

    private static FakeGithubRepositoryImpl fakeGithubRepository;

    private FakeGithubRepositoryImpl() {
    }

    public static FakeGithubRepositoryImpl getGithubRepository() {
        if (fakeGithubRepository == null) {
            synchronized (FakeGithubRepositoryImpl.class) {
                fakeGithubRepository = new FakeGithubRepositoryImpl();
            }
        }
        return fakeGithubRepository;
    }

    @Override
    public Observable<SearchResponse> searchRepos(@NonNull String searchedText) {
        return Observable.create(subscriber -> {
            List<RepoNameResponse> resultList = new ArrayList<>();
            resultList.add(new RepoNameResponse("repo 1"));
            resultList.add(new RepoNameResponse("repo 2"));
            resultList.add(new RepoNameResponse("repo 3"));

            subscriber.onNext(new SearchResponse(resultList.size(), resultList));
            subscriber.onComplete();
        });
    }
}