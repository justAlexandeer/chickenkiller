package com.myprog.chickenkiller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myprog.chickenkiller.API.JsonPlaceHolderApi;
import com.myprog.chickenkiller.Data.DataManager;
import com.myprog.chickenkiller.models.ResultToken;
import com.myprog.chickenkiller.models.Token;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextLogin, editTextRegistration;
    private Button buttonLogin, buttonRegistration;
    private String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,10}$";
    public static final String BASE_URL = "http://fafklasd.chickenkiller.com:8000/";
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init(){
        editTextLogin = findViewById(R.id.activityRegistration_EditTextEmail);
        editTextRegistration = findViewById(R.id.activityRegistration_EditTextPassword);

        buttonLogin = findViewById(R.id.activityRegistration_ButtonLogin);
        buttonRegistration = findViewById(R.id.activityRegistration_ButtonRegister);

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonRegistrationClick();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonLoginClick();
            }
        });
    }

    private boolean verifyFields(){
        String login = String.valueOf(editTextLogin.getText());
        String password = String.valueOf(editTextRegistration.getText());

        if(login.length() == 0){
            Toast.makeText(RegistrationActivity.this, "Введите логин", Toast.LENGTH_LONG).show();
            return false;
        }

        if(password.length() == 0) {
            Toast.makeText(RegistrationActivity.this, "Введите пароль", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void onButtonLoginClick(){
        if(!verifyFields())
            return;

        String login = String.valueOf(editTextLogin.getText());
        String password = String.valueOf(editTextRegistration.getText());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<ResultToken> call = jsonPlaceHolderApi.logIn(login, password);

        call.enqueue(new Callback<ResultToken>() {
            @Override
            public void onResponse(Call<ResultToken> call, Response<ResultToken> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "Response not successful", Toast.LENGTH_LONG).show();
                    return;
                }
                List<Token> token = response.body().getTokens();
                if(token.size() == 0 ) {
                    Toast.makeText(RegistrationActivity.this, "Неизвестная ошибка", Toast.LENGTH_LONG).show();
                    return;
                }
                Token tokenNow = token.get(0);
                DataManager.writeToken(tokenNow.getTxt(), RegistrationActivity.this);

                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                /*Intent intent = new Intent(RegistrationActivity.this, MyPageInfo.class);
                intent.putExtra("numberActivity", 1);
                startActivity(intent);*/
            }

            @Override
            public void onFailure(Call<ResultToken> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, String.valueOf(t), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onButtonRegistrationClick(){
        if(!verifyFields())
            return;

        String login = String.valueOf(editTextLogin.getText());
        String password = String.valueOf(editTextRegistration.getText());

        boolean emailValidate = verifyEmail(login);
        boolean passwordValidate = verifyPassword(password);

        if(!emailValidate){
            Toast.makeText(RegistrationActivity.this, "Проверьте правильность ввода Email", Toast.LENGTH_LONG).show();
            return;
        }

        if(!passwordValidate){
            Toast.makeText(RegistrationActivity.this, "Пароль должен состоять мин из 6 символов и однной заглвной буквы ",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(emailValidate && passwordValidate){
            registerUser(login, password);
        }
    }

    private boolean verifyEmail(String login){
        Pattern pattern  = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    private boolean verifyPassword(String password){
        Pattern pattern  = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private void registerUser(String login, String password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<Void> call = jsonPlaceHolderApi.postUser(login, password);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RegistrationActivity.this, String.valueOf(response.code()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "Вы успешно зарегистрировались", Toast.LENGTH_LONG).show();
            }
        });
    }
}
