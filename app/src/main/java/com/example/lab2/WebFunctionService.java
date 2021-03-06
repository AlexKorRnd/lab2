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
    public static String BASE_URL = "https://cs7055.vk.me/c414416/u55166008/docs/9edeb841bb0f/test.json?extra=Qt89w2RoDhcRhS8xA7H-6UGrhSoUcU-9rwU6HCMuYi8dsOfEfjRFAi3Eim4aFjNjdy7GjMMdCigSianqxO4lNh3j0nVVnRRqKDMPAbt7bqpuo1erwgInHpS6T0wkMqFs6hP8X1FTyKb9&dl=1";

    Gson gson = new GsonBuilder()
           // .setExclusionStrategies(getExclusionStrategy())
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

    public Gson getGson() {
        return gson;
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
