package com.coppermobile.myapplication.network.models.response;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SearchResponse implements Serializable {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("items")
    private List<RepoNameResponse> items;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setItems(List<RepoNameResponse> items) {
        this.items = items;
    }

    public List<RepoNameResponse> getItems() {
        return items;
    }

    public SearchResponse() {
    }

    public SearchResponse(@NonNull Integer totalCount, @NonNull List<RepoNameResponse> repoNameResponseList) {
        this.totalCount = totalCount;
        this.items = repoNameResponseList;
    }
}