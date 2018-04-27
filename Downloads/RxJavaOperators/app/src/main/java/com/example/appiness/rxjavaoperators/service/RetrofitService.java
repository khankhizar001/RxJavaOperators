package com.example.appiness.rxjavaoperators.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by appiness on 24/4/18.
 */

public class RetrofitService {

    private static Retrofit retrofit = null;
    public static Retrofit getClient() {


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.androidhive.info/json/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}

