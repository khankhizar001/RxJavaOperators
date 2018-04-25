package com.example.appiness.rxjavaoperators.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by appiness on 24/4/18.
 */

public class RetrofitService {

    APIHelper retrofitApi;

    public APIHelper makeRetrofitService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.171/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofitApi = retrofit.create(APIHelper.class);

        return retrofitApi;
      }
}

