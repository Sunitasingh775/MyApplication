package com.coppermobile.myapplication.repository;

import android.support.annotation.NonNull;

import com.coppermobile.myapplication.network.models.response.SearchResponse;

import io.reactivex.Observable;

public interface GithubRepository {

    Observable<SearchResponse> searchRepos(@NonNull final String searchedText);
}