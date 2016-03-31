package com.example.lab2;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

// этот класс взят полностью из примера в лабе
public interface WebService {
    @GET("/")
    void getFile(Callback<List<Item>> c);
}
