package com.example.appiness.rxjavaoperators.service;

import com.example.appiness.rxjavaoperators.pojo.Price;
import com.example.appiness.rxjavaoperators.pojo.Ticket;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by appiness on 24/4/18.
 */

public interface APIHelper {

    @GET("airline-tickets.php")
    Observable<List<Ticket>> getTickets(@Query("from") String from, @Query("to") String to);

    @GET("airline-tickets-price.php")
    Observable<Price> getPrice(@Query("flight_number") String flightNumber, @Query("from") String from, @Query("to") String to);

}
