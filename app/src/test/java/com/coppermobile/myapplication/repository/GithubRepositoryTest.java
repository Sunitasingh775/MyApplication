package com.coppermobile.myapplication.repository;

import com.coppermobile.myapplication.Injection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GithubRepositoryTest {

    private GithubRepository gitHubRepository;

//    @Rule
//    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        gitHubRepository = Injection.getGithubRepository();
    }

    @Test
    public void searchRepos() {

        String searchQuery = "some query";

        gitHubRepository.searchRepos(searchQuery);

        Mockito.verify(gitHubRepository, Mockito.times(1)).searchRepos(searchQuery);
    }

    @After
    public void afterAllTestMethods() throws Exception {
    }
}