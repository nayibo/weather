package com.weather.nayibo.weather.retrofit;

import com.weather.nayibo.weather.homepage.HomeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nayibo on 2017/7/25.
 */

public interface RetrofitService {
    @GET("s6/weather/forecast")
    Call<HomeModel> getForecast(@Query("location") String location, @Query("key") String key, @Query("lang") String language, @Query("unit") String unit);
}
