package com.myprog.chickenkiller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myprog.chickenkiller.API.JsonPlaceHolderApi;
import com.myprog.chickenkiller.Data.DataManager;
import com.myprog.chickenkiller.adapters.mainNewsAdapter;
import com.myprog.chickenkiller.models.Post;
import com.myprog.chickenkiller.models.ResultAllNews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    EditText editText;
    Button buttonSearch, buttonMyPage;
    int countPagination = 0;
    private String token;

    public static final String BASE_URL = "http://fafklasd.chickenkiller.com:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.mainRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        editText = findViewById(R.id.activityMain_search);
        buttonSearch = findViewById(R.id.activityMain_buttonSearch);
        buttonMyPage = findViewById(R.id.activityMain_buttonMyPage);
        recyclerView.setLayoutManager(layoutManager);

        token = DataManager.readToken(MainActivity.this);
        String url = BASE_URL + token + "/" + countPagination + "/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        startAllNews();
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSearch();
            }
        });
        buttonMyPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startMyPage();
            }
        });
    }

    public void startAllNews(){
        Call<ResultAllNews> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<ResultAllNews>() {
            @Override
            public void onResponse(Call<ResultAllNews> call, Response<ResultAllNews> response) {
                List<Post> posts = response.body().getPost();
                recyclerView.setAdapter(new mainNewsAdapter(posts, MainActivity.this));
            }
            @Override
            public void onFailure(Call<ResultAllNews> call, Throwable t) {
                Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void startSearch(){
        String content = editText.getText().toString();

        if(!content.isEmpty()){
            Call<ResultAllNews> call = jsonPlaceHolderApi.getSearch(content);
            call.enqueue(new Callback<ResultAllNews>() {
                @Override
                public void onResponse(Call<ResultAllNews> call, Response<ResultAllNews> response) {
                    if(response.body().getPost() != null) {
                        List<Post> posts = response.body().getPost();
                        recyclerView.setAdapter(new mainNewsAdapter(posts, MainActivity.this));
                    }
                }
                @Override
                public void onFailure(Call<ResultAllNews> call, Throwable t) {
                    Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Toast.makeText(this, "Введите что нибдуь в строку поиска", Toast.LENGTH_LONG).show();
        }
    }

    public void startMyPage(){
        Intent intent = new Intent(MainActivity.this, MyPage.class);
        intent.putExtra("Token", token);
        startActivity(intent);
    }
}
