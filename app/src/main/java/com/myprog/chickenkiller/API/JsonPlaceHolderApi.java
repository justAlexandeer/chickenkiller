package com.myprog.chickenkiller.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

import com.myprog.chickenkiller.models.ResultAllNews;
import com.myprog.chickenkiller.models.ResultMyPage;
import com.myprog.chickenkiller.models.ResultToken;

public interface JsonPlaceHolderApi {

    @GET("news")
    Call<ResultAllNews> getPosts();

    @GET("news/filter")
    Call<ResultAllNews> getNewById(@Query("id_of_new") int id_of_new);

    @GET("news/search")
    Call<ResultAllNews> getSearch(@Query("content") String content);

    @POST("registration")
    Call<Void> postUser(
            @Query("login") String login,
            @Query("password") String password
    );

    @GET("login")
    Call<ResultToken> logIn(
            @Query("login") String login,
            @Query("password") String password
    );

    @GET("users/me")
    Call<ResultMyPage> getMyPage();

    @PUT("USERPUT/users")
    Call<Void> changeUserInfo(
            @Query("first_name") String firstName,
            @Query("second_name") String secondName,
            @Query("image") String image
    );

    @POST("comments")
    Call<Void> addComment(
            @Query("text_of_comment") String comment
    );
}
