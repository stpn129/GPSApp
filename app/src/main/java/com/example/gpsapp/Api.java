package com.example.gpsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("weather")
    Call<Results> getRes(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String appId);

}
