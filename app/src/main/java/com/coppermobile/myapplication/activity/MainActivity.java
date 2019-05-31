package com.coppermobile.myapplication.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.EditorInfo;

import com.coppermobile.myapplication.R;
import com.coppermobile.myapplication.adapter.RepoAdapter;
import com.coppermobile.myapplication.viewmodel.RepoViewModel;
import com.coppermobile.myapplication.ViewModelFactory;
import com.coppermobile.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private RepoAdapter repoAdapter;
    private ActivityMainBinding binding;
    private RepoViewModel repoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        observeViewModel();
        setUpRepoRecyclerView();

        binding.etSearchRepo.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                repoViewModel.searchRepos(binding.etSearchRepo.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        repoViewModel = getRepoViewModel();
        binding.setRepoViewModel(repoViewModel);
    }

    private void observeViewModel() {
        repoViewModel.getSearchResponse().observe(this, searchResponse -> {
            if (searchResponse != null) repoAdapter.updateList(searchResponse.getItems());
        });
    }

    public RepoViewModel getRepoViewModel() {
        ViewModelFactory factory = ViewModelFactory.getViewModelFactory();
        return ViewModelProviders.of(this, factory).get(RepoViewModel.class);
    }

    private void setUpRepoRecyclerView() {
        RecyclerView rvRepo = binding.rvRepo;
        repoAdapter = new RepoAdapter();
        rvRepo.setAdapter(repoAdapter);
    }
}
