package com.coppermobile.myapplication.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.coppermobile.myapplication.RxSchedulersOverrideRule;
import com.coppermobile.myapplication.network.models.response.SearchResponse;
import com.coppermobile.myapplication.repository.RealGithubRepositoryImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RepoViewModelTest {

    private RepoViewModel repoViewModel;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    // Test rule for making the RxJava to run synchronously in unit test
    @Rule
    public final RxSchedulersOverrideRule schedulers = new RxSchedulersOverrideRule();

    @Mock
    private RealGithubRepositoryImpl realGithubRepository;

    @Mock
    private Observer<SearchResponse> observer;

    @Before
    public void setUp() throws Exception {
        repoViewModel = new RepoViewModel(realGithubRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void searchGitHubRepos() {

        String searchQuery = "some query";

        Mockito.when(realGithubRepository.searchRepos(searchQuery)).thenReturn(Observable.just(new SearchResponse()));

        repoViewModel.getSearchResponse().observeForever(observer);

        repoViewModel.searchRepos(searchQuery);

        verify(realGithubRepository, Mockito.times(1)).searchRepos(searchQuery);
    }

    @After
    public void afterAllTestMethods() throws Exception {
    }
}