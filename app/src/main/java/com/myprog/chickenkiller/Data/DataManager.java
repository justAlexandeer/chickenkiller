package com.myprog.chickenkiller.Data;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataManager {

    public static void writeToken(String token, Context context){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("Token.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(token.getBytes());
            fileOutputStream.close();
            Log.d("DataManager", "Файл сохранен");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readToken(Context context){
        String token;
        try {
            FileInputStream fileInputStream = context.openFileInput("Token.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            token = bufferedReader.readLine();
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
