package com.example.isicine.rest;

import com.example.isicine.model.CineJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CineAPI {

    /**
     * Call a webservice with dynamic path
     * Exemple :
     * "https://api.myjson.com/bins/31245"
     * base url : https://api.myjson.com/
     * path : bins/{id}
     * where id = 31245
     * So the method will be called using getNews("31235");
     */

    @GET("pam/cine.json")
    Call<CineJSON> getCine();

    /**
     * Call a webservice with a static path
     */
    @GET("pam/cine.json")
    Call<String> getFile();
}
