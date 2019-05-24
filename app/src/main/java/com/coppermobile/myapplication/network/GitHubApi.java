package com.coppermobile.myapplication.network;

import com.coppermobile.myapplication.network.models.response.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET(URLs.SEARCH_REPO)
    Observable<SearchResponse> getRepos(@Query("q") String searchedText);
}
