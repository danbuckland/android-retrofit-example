package com.blocksolid.retrofittutorial.api;

import com.blocksolid.retrofittutorial.model.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {
    @GET("users/{user}")
    //The second part of the URL
    Call<GitHubUser> getFeed(@Path("user") String user);
    //String user is for passing values from the EditText e.g. user="danbuckland", "google"
    //Response is the response from the server which is now in the POJO
}
