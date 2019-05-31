package com.coppermobile.myapplication.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubClient {

    private GitHubApi gitHubApi;
    private static GitHubClient gitHubClient;

    public GitHubApi getGitHubApi() {

        if (gitHubApi == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.retryOnConnectionFailure(true);

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addNetworkInterceptor(loggingInterceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URLs.BASE_URL)
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            gitHubApi = retrofit.create(GitHubApi.class);
        }
        return gitHubApi;
    }

    public static GitHubClient getGitHubClient() {
        if (gitHubClient == null) {
            gitHubClient = new GitHubClient();
        }
        return gitHubClient;
    }
}
