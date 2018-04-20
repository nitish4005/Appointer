package com.example.android.appointer.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Prasad on 27-Mar-18.
 */

public class HttpRetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("Url to be written here")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        return retrofit;
    }
}
