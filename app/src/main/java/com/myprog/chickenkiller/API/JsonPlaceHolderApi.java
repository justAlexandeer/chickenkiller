package com.myprog.chickenkiller.API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.myprog.chickenkiller.models.ResultAllNews;

public interface JsonPlaceHolderApi {

    @GET("news")
    Call<ResultAllNews> getPosts();

    @GET("news/filter")
    Call<ResultAllNews> getNewById(@Query("id_of_new") int id_of_new);
}
