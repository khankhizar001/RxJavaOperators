package com.example.appiness.rxjavaoperators.service;

import com.example.appiness.rxjavaoperators.pojo.FirstJSON;
import com.example.appiness.rxjavaoperators.pojo.SecondJSON;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by appiness on 24/4/18.
 */

public interface APIHelper {

    @GET("firsjson.json")
    Observable<List<FirstJSON.Item>> getItems();

    @GET("secondjson.json")
    Observable<List<SecondJSON.Item>> getItems2();

}
