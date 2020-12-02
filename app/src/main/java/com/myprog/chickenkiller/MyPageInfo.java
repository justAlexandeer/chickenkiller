package com.myprog.chickenkiller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myprog.chickenkiller.API.JsonPlaceHolderApi;
import com.myprog.chickenkiller.Data.DataManager;
import com.myprog.chickenkiller.adapters.ImageAvatarAdapter;
import com.myprog.chickenkiller.listener.ImageAvatarListener;
import com.myprog.chickenkiller.models.ResultToken;
import com.myprog.chickenkiller.models.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPageInfo extends AppCompatActivity implements ImageAvatarListener {

    private EditText editTextName, editTextSecondName;
    private Button buttonSave;
    private int numberActivityFrom;
    private RecyclerView recyclerView;
    private ArrayList<String> imageAvatar;
    private RecyclerView.LayoutManager layoutManager;
    private String imageAvatarChoose = null;
    private ImageAvatarAdapter imageAvatarAdapter;
    String url;
    public static final String BASE_URL = "http://fafklasd.chickenkiller.com:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_info);
        init();
    }

    public void init(){
        editTextName = findViewById(R.id.activityMyPageInfo_FirstName);
        editTextSecondName = findViewById(R.id.activityMyPageInfo_SecondName);
        buttonSave = findViewById(R.id.activityMyPageInfo_ButtonSave);
        recyclerView = findViewById(R.id.activityMyPageInfo_RecyclerView);

        imageAvatar = new ArrayList<String>();
        imageAvatar.add("https://i.ibb.co/wJX600C/5861.gif");
        imageAvatar.add("https://i.ibb.co/9yxGRpL/5855.gif");
        imageAvatar.add("https://i.ibb.co/hm8y09k/5856.gif");
        imageAvatar.add("https://i.ibb.co/jZv1vh0/5857.gif");
        imageAvatar.add("https://i.ibb.co/89MDvL5/5858.gif");
        imageAvatar.add("https://i.ibb.co/Ng0rdBL/5859.gif");
        imageAvatar.add("https://i.ibb.co/pK1Nxcj/5860.gif");

        imageAvatarAdapter = new ImageAvatarAdapter(imageAvatar);

        imageAvatarAdapter.setListener(this);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAvatarAdapter);

        Intent intent = getIntent();
        numberActivityFrom = intent.getIntExtra("numberActivity", 0);

        String token = DataManager.readToken(MyPageInfo.this);
        url = BASE_URL + token + "/";

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChange();
            }
        });
    }

    public void saveChange(){
        if(!verifyFields())
            return;

        String firstName = String.valueOf(editTextName.getText());
        String secondName = String.valueOf(editTextSecondName.getText());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Toast.makeText(MyPageInfo.this, firstName, Toast.LENGTH_SHORT).show();
        Toast.makeText(MyPageInfo.this, secondName, Toast.LENGTH_SHORT).show();

        Call<Void> call = jsonPlaceHolderApi.changeUserInfo(firstName, secondName,imageAvatarChoose);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                    return;
                if(numberActivityFrom == 1) {
                    Intent intent = new Intent(MyPageInfo.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(numberActivityFrom == 2) {
                    Intent intent = new Intent(MyPageInfo.this, MyPage.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MyPageInfo.this, String.valueOf(t), Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean verifyFields(){
        if(String.valueOf(editTextName.getText()).length() >= 3 &&
                String.valueOf(editTextSecondName.getText()).length() >= 3)
            return true;
        else{
            Toast.makeText(MyPageInfo.this, "Введите Имя и фамилию", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void setImageAvatar(String imageAvatar) {
        this.imageAvatarChoose = imageAvatar;
    }
}
