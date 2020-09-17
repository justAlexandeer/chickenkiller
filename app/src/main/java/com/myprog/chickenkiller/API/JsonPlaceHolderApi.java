package com.myprog.chickenkiller.API;
import com.myprog.chickenkiller.models.Post;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("users")
    Call<Post> getPosts();

}
