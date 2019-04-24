package com.pretty.strawberry.retrofit2tutorial.network;

import com.pretty.strawberry.retrofit2tutorial.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/posts")
    Call<List<Posts>> getAllPosts();

}
