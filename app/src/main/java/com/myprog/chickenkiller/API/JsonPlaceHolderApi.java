package com.myprog.chickenkiller.API;
import com.myprog.chickenkiller.models.Result;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("news")
    Call<Result> getPosts();

}
