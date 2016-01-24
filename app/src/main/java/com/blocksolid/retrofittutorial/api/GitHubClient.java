package com.blocksolid.retrofittutorial.api;

import com.blocksolid.retrofittutorial.model.Contributor;
import com.blocksolid.retrofittutorial.model.GitModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubClient {
    @GET("users/{user}")
    //The second part of the URL
    Call<GitModel>  getFeed(@Path("user") String user);
    //String user is for passing values from the EditText e.g. user="danbuckland", "google"
    //Response is the response from the server which is now in the POJO

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
