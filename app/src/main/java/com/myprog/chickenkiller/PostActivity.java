package com.myprog.chickenkiller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myprog.chickenkiller.API.JsonPlaceHolderApi;
import com.myprog.chickenkiller.Data.DataManager;
import com.myprog.chickenkiller.adapters.postCommentsAdapter;
import com.myprog.chickenkiller.models.Comment;
import com.myprog.chickenkiller.models.Post;
import com.myprog.chickenkiller.models.ResultAllNews;

import java.util.ArrayList;
import java.util.Collections;
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
    EditText editTextComment;
    Button buttonAddComment;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Retrofit retrofit;

    Call<ResultAllNews> callPost;

    private int id;
    private String token;
    public static final String BASE_URL = "http://fafklasd.chickenkiller.com:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        init();
    }

    private void init() {
        Intent intent = getIntent();
        id = intent.getIntExtra("PostById", 0);
        int countPagination = 0;
        token = DataManager.readToken(PostActivity.this);
        String url = BASE_URL + token + "/" + countPagination + "/";

        textViewName = findViewById(R.id.activityPost_PostName);
        textViewText = findViewById(R.id.activityPost_PostText);
        textViewAuthor = findViewById(R.id.activityPost_PostAuthor);
        textViewDate = findViewById(R.id.activityPost_PostDate);
        recyclerView = findViewById(R.id.activityPost_RecyclerView);
        editTextComment = findViewById(R.id.activityPost_EditTextComment);
        buttonAddComment = findViewById(R.id.activityPost_ButtonAddComment);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        callPost = jsonPlaceHolderApi.getNewById(id);

        buttonAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setComment();
            }
        });

        getPost();
    }


    private void getPost() {
        callPost.enqueue(new Callback<ResultAllNews>() {
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

    private void setComment() {
        String comment = String.valueOf(editTextComment.getText());
        if(comment.length() == 0){
            Toast.makeText(PostActivity.this, "Комментарий не должен быть пустым", Toast.LENGTH_LONG).show();
            return;
        }
        int countPagination = 1;

        String url = BASE_URL + token + "/" + countPagination + "/" + "news" + "/" + id+ "/";
        //Toast.makeText(PostActivity.this, url, Toast.LENGTH_LONG).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Void> call = jsonPlaceHolderApi.addComment(comment);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                    Toast.makeText(PostActivity.this, String.valueOf(response.code()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("String", String.valueOf(t));
            }
        });

        callPost.clone().enqueue(new Callback<ResultAllNews>() {
            @Override
            public void onResponse(Call<ResultAllNews> call, Response<ResultAllNews> response) {
                List<Post> list = response.body().getPost();
                Post postNow = list.get(0);
                List<Comment> comments = postNow.getComments();
                if(comments.get(0) != null) {
                    recyclerView.setAdapter(new postCommentsAdapter(comments));
                }
            }

            @Override
            public void onFailure(Call<ResultAllNews> call, Throwable t) {

            }
        });
    }
}
