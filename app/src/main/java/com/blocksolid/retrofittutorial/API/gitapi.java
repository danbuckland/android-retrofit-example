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
    //here is the other url part.best way is to start using /

    void getFeed(@Path("user") String user, Callback<GitModel> response);
    //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}
