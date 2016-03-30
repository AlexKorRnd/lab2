package com.example.lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
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
                ArrayList<Item> items = new Gson().fromJson(string, new TypeToken<ArrayList<Item>>() {
                }.getType());
                for (Item item : items) {
                    item.save();
                }

                showData(string, items.size());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("log", error.getMessage(), error);
                FlowCursorList<Item> flowCursorList = new FlowCursorList<>(50, SQLite.select()
                        .from(Item.class));
                if (!flowCursorList.isEmpty()) {
                    List<Item> items = flowCursorList.getAll();
                    Gson gson = new Gson();
                    String itemsJson = gson.toJson(items);
                    textView.setText(itemsJson);
                    showData(itemsJson, items.size());
                }

            }
        });
    }

    private void showData(String text, int count) {
        textView.setText(text);
        Toast.makeText(MainActivity.this, "Загружено элементов: " + count, Toast.LENGTH_LONG).show();
    }
}
