package com.example.notimelikethepresent.Connection;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("weather")
    Observable<WeatherResponse> getWeatherByLocalization(@Query("lat") String lat,
                                                         @Query("lon") String lng,
                                                         @Query("appid") String appid
    );
}
