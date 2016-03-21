package com.example.lab2;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

// этот класс взят полностью из примера в лабе
public interface WebService {
    @GET("/test.json")
    void getFile(Callback<Response> c);
}
