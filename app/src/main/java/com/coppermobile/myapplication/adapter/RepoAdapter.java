package com.coppermobile.myapplication.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coppermobile.myapplication.R;
import com.coppermobile.myapplication.network.models.response.RepoNameResponse;
import com.coppermobile.myapplication.databinding.LayoutRepoNameBinding;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MyViewHolder> {

    private List<RepoNameResponse> repoNameResponseList;

    public void updateList(List<RepoNameResponse> repoNameResponseList) {
        this.repoNameResponseList = repoNameResponseList;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new MyViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.layout_repo_name, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.layoutRepoNameBinding.setRepoName(repoNameResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return repoNameResponseList != null && !repoNameResponseList.isEmpty() ? repoNameResponseList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private LayoutRepoNameBinding layoutRepoNameBinding;

        MyViewHolder(@NonNull LayoutRepoNameBinding layoutRepoNameBinding) {
            super(layoutRepoNameBinding.getRoot());
            this.layoutRepoNameBinding = layoutRepoNameBinding;
        }
    }
}
