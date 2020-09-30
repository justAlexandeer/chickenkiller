package com.myprog.chickenkiller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.myprog.chickenkiller.API.JsonPlaceHolderApi;
import com.myprog.chickenkiller.adapters.postCommentsAdapter;
import com.myprog.chickenkiller.models.Comment;
import com.myprog.chickenkiller.models.Post;
import com.myprog.chickenkiller.models.ResultAllNews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    TextView textViewName;
    TextView textViewText;
    TextView textViewAuthor;
    TextView textViewDate;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        int id = intent.getIntExtra("PostById", 0);
        textViewName = findViewById(R.id.activityPost_PostName);
        textViewText = findViewById(R.id.activityPost_PostText);
        textViewAuthor = findViewById(R.id.activityPost_PostAuthor);
        textViewDate = findViewById(R.id.activityPost_PostDate);
        recyclerView = findViewById(R.id.activityPost_RecyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fafklasd.chickenkiller.com:8000/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJ1c2VyUm9sZSI6IkF1dGhvciIsInVzZXJfaWQiOjJ9.Ly4Qz1y-qjaDtRL90-XkNBSPZaA2e0F2hxmaXuMMbXk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ResultAllNews> call = jsonPlaceHolderApi.getNewById(id);

        call.enqueue(new Callback<ResultAllNews>() {
            @Override
            public void onResponse(Call<ResultAllNews> call, Response<ResultAllNews> response) {
                List<Post> list = response.body().getPost();
                Post postNow = list.get(0);

                String content = postNow.getName();
                textViewName.append(content);
                content = postNow.getTextOfNew();
                textViewText.append(content);
                content = postNow.getAutorId().getUserIdAutors().getFirstName() + " "
                        +  postNow.getAutorId().getUserIdAutors().getSecondName();
                textViewAuthor.append(content);
                textViewDate.append(postNow.getDateOfCreateNew());

                List<Comment> comments = postNow.getComments();
                if(comments.get(0) != null) {
                    recyclerView.setAdapter(new postCommentsAdapter(comments));
                }
            }

            @Override
            public void onFailure(Call<ResultAllNews> call, Throwable t) {
                Log.d("String", String.valueOf(t));
            }
        });
    }
}
