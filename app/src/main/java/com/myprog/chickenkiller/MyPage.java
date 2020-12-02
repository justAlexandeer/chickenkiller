package com.myprog.chickenkiller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myprog.chickenkiller.API.JsonPlaceHolderApi;
import com.myprog.chickenkiller.Data.DataManager;
import com.myprog.chickenkiller.models.ResultMyPage;
import com.myprog.chickenkiller.models.UserIdAutors;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPage extends AppCompatActivity {

    private String token;
    public static final String BASE_URL = "http://fafklasd.chickenkiller.com:8000/";
    private Retrofit retrofit;
    private String url;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private UserIdAutors userNow;
    private TextView textViewName, textViewDateRegistration;
    private Button buttonEdit;
    private ImageView imageViewAvatar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        init();
    }

    public void init(){
        textViewName = findViewById(R.id.activityMyPage_Name);
        textViewDateRegistration = findViewById(R.id.activityMyPage_DateRegistration);
        imageViewAvatar = findViewById(R.id.activityMyPage_Avatar);
        buttonEdit = findViewById(R.id.activityMyPage_ButtonEdit);
        toolbar = findViewById(R.id.activityMyPage_Toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        token = DataManager.readToken(MyPage.this);
        int countPagination = 0;
        url = BASE_URL + token + "/" + countPagination + "/";

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        String date = String.format(getResources().getString(R.string.myPageActivity_DateRegistration), "");
        textViewDateRegistration.setText(date);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPage.this, MyPageInfo.class);
                intent.putExtra("numberActivity", 2);
                startActivity(intent);
                finish();
            }
        });

        getUser();
    }

    public void updateUserInfo(){
        String name = userNow.getFirstName() + " " + userNow.getSecondName();
        textViewName.setText(name);
        String date = String.format(getResources().getString(R.string.myPageActivity_DateRegistration), userNow.getDateOfCreateUser());
        textViewDateRegistration.setText(date);
        Picasso.get().load(userNow.getImage()).into(imageViewAvatar);
    }

    public void getUser(){
        Call<ResultMyPage> call = jsonPlaceHolderApi.getMyPage();

        call.enqueue(new Callback<ResultMyPage>() {
            @Override
            public void onResponse(Call<ResultMyPage> call, Response<ResultMyPage> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MyPage.this, String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                    return;
                }
                List<UserIdAutors> user = response.body().getUser();
                userNow = user.get(0);
                updateUserInfo();
            }
            @Override
            public void onFailure(Call<ResultMyPage> call, Throwable t) {
                Toast.makeText(MyPage.this, String.valueOf(t), Toast.LENGTH_LONG).show();
            }
        });
    }
}
