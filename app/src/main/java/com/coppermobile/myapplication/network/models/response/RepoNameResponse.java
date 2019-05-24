package com.coppermobile.myapplication.network.models.response;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class RepoNameResponse {

    @SerializedName("full_name")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public RepoNameResponse(@NonNull String fullName) {
        this.fullName = fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}