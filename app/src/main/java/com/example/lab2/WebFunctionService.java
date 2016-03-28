package com.example.lab2;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


// этот класс взят полностью из примера в лабе
public class WebFunctionService {
    public static String BASE_URL = "https://doc-0c-c4-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/amgnq349u3kb9m90ovb4bvlt8req36ua/1459144800000/17048787405590985885/*/0B9KCR_vJX2EDbUdHYV90LW1ER00?e=download";

    Gson gson = new GsonBuilder()
            .setExclusionStrategies(getExclusionStrategy())
            .create();

    private RestAdapter mRest = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .setConverter(new GsonConverter(gson))
            .setClient(new OkClient(new OkHttpClient()))
            .build();

    private WebService mWebService = mRest.create(WebService.class);

    public WebService getmWebService() {
        return mWebService;
    }

    public static String responseToString(Response response) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private ExclusionStrategy getExclusionStrategy() {
        return new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(Item.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };
    }
}
