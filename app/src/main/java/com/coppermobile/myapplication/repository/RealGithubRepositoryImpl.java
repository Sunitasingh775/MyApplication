package com.coppermobile.myapplication.repository;

import android.support.annotation.NonNull;

import com.coppermobile.myapplication.network.models.response.SearchResponse;
import com.coppermobile.myapplication.network.GitHubClient;

import io.reactivex.Observable;

public class RealGithubRepositoryImpl implements GithubRepository {

    private static RealGithubRepositoryImpl realGithubRepository;

    private RealGithubRepositoryImpl() {
    }

    public static RealGithubRepositoryImpl getGithubRepository() {
        if (realGithubRepository == null) {
            synchronized (RealGithubRepositoryImpl.class) {
                realGithubRepository = new RealGithubRepositoryImpl();
            }
        }
        return realGithubRepository;
    }

    @Override
    public Observable<SearchResponse> searchRepos(@NonNull String searchedText) {
        return GitHubClient.getGitHubClient().getGitHubApi().getRepos(searchedText);
    }
}
