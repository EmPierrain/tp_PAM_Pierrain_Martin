package com.example.isicine.rest;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private CineAPI cineAPI;

    public CineAPI getCineAPI() {
        return cineAPI;
    }
    //pam/cine.json
    private ApiHelper() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://etudiants.openium.fr/").addConverterFactory(GsonConverterFactory.create()).build();
        cineAPI = retrofit.create(CineAPI.class);
        Log.d("ApiHelper", retrofit.toString());

    }

    private static volatile ApiHelper instance;


    public static synchronized ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }
}
