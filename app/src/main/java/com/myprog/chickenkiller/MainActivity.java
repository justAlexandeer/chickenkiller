package com.myprog.chickenkiller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.myprog.chickenkiller.API.JsonPlaceHolderApi;
import com.myprog.chickenkiller.models.Post;
import com.myprog.chickenkiller.models.Result;
import com.myprog.chickenkiller.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fafklasd.chickenkiller.com:8000/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJ1c2VyUm9sZSI6IkF1dGhvciIsInVzZXJfaWQiOjJ9.Ly4Qz1y-qjaDtRL90-XkNBSPZaA2e0F2hxmaXuMMbXk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Result> call = jsonPlaceHolderApi.getPosts();


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<Post> posts = response.body().getPost();
                for(Post post: posts){
                    String content = "";
                    content+= "\n\n\n";
                    content+= post.getIdOfNew() + "\n";
                    content+= post.getTextOfNew() + "\n";

                    mainText.append(content);
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                mainText.setText(t.getMessage());
            }
        });
    }
}
