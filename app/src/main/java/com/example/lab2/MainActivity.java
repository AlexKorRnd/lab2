package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // добавляем LayoutManager для RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                intent.putExtra(DescriptionActivity.EXTRA_ITEM, adapter.getItem(position));
                startActivity(intent);
            }
        }));

        loadData();
    }

    private void loadData() {
        final WebFunctionService webFunctionService = new WebFunctionService();
        webFunctionService.getmWebService().getFile(new Callback<List<Item>>() {
            @Override
            public void success(List<Item> items, Response response) {
                adapter.addAll(items);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("log", error.getMessage(), error);

            }
        });
        /*webFunctionService.getmWebService().getFile(new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                // перевели response в строку
                String string = WebFunctionService.responseToString(response);

                Realm realm = App.getRealm();

                //realm.beginTransaction();
                List<Item> items = webFunctionService.gson.fromJson(string, new TypeToken<List<Item>>() {
                }.getType());
                adapter.addAll(items);
               // realm.createOrUpdateAllFromJson(Item.class, string);
               // realm.commitTransaction();


            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("log", error.getMessage(), error);

                *//*if (!flowCursorList.isEmpty()) {
                    List<Item> items = flowCursorList.getAll();
                    Gson gson = new Gson();
                    String itemsJson = gson.toJson(items);
                    textView.setText(itemsJson);
                    showData(itemsJson, items.size());
                }*//*

            }
        });*/
    }



}
