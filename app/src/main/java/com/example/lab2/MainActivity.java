package com.example.lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.textView);
        loadData();

    }

    private void loadData() {
        final WebFunctionService webFunctionService = new WebFunctionService();
        webFunctionService.getmWebService().getFile(new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                // перевели response в строку
                String string = WebFunctionService.responseToString(response);
                textView.setText(string);
                List<Item> items = new Gson().fromJson(string, new TypeToken< List<Item>>(){}.getType());
                Toast.makeText(MainActivity.this, "Загружено элементов: " + items.size(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("log", error.getMessage(), error);
            }
        });
    }

}
