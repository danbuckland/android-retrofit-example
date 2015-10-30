package com.blocksolid.retrofittutorial.api;

import com.blocksolid.retrofittutorial.model.GitModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Dan Buckland on 26/10/2015.
 */
public interface GitApi {
    @GET("/users/{user}")
    //The second part of the URL

    void getFeed(@Path("user") String user, Callback<GitModel> response);
    //String user is for passing values from the EditText e.g. user="danbuckland", "google"
    //Response is the response from the server which is now in the POJO
}
