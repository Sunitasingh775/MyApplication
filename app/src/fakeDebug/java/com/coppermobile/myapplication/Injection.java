package com.coppermobile.myapplication;

import com.coppermobile.myapplication.repository.GithubRepository;

import com.coppermobile.myapplication.repository.FakeGithubRepositoryImpl;

public class Injection {

    private Injection() {
    }

    public static GithubRepository getGithubRepository(){
        return FakeGithubRepositoryImpl.getGithubRepository();
    }
}