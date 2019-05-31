package com.coppermobile.myapplication.di;

import com.coppermobile.myapplication.network.GitHubApi;
import com.coppermobile.myapplication.network.URLs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class NetModule {

    /**
     * Get the GSON object for parsing the data from/to JSON to Java and vice-versa
     *
     * @return the object of the GSON class.
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    /**
     * Get the OKHTTP object to be used by the retrofit client for adding the interceptor and timeout.
     *
     * @return the object of the OkHttp class
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.retryOnConnectionFailure(true);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addNetworkInterceptor(loggingInterceptor);
        return httpClient.build();
    }

    /**
     * Get the retrofit object for networking
     *
     * @param gson         object to parse the data from/to JSON
     * @param okHttpClient to add the interceptors and timeout to the retrofit builder.
     * @return retrofit object to be used for networking
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URLs.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    /**
     * Get the GitHubApi object
     *
     * @param retrofit object for networking
     * @return the GitHubApi object
     */
    @Provides
    @Singleton
    GitHubApi getGitHubApi(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }
}
