package com.coppermobile.myapplication.repository;

import com.coppermobile.myapplication.network.models.response.SearchResponse;

import io.reactivex.Observable;

public interface GithubRepository {

    Observable<SearchResponse> searchRepos(final String searchedText);
}