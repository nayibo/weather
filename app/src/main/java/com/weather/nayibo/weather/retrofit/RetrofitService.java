package com.weather.nayibo.weather.retrofit;

import com.weather.nayibo.weather.vo.WeatherBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nayibo on 2017/7/25.
 */

public interface RetrofitService {
    @GET("s6/weather/forecast")
    Call<WeatherBean> getForecastCall(@Query("location") String location, @Query("key") String key, @Query("lang") String language, @Query("unit") String unit);

    @GET("s6/weather/forecast")
    Observable<WeatherBean> getForecast(@Query("location") String location, @Query("key") String key, @Query("lang") String language, @Query("unit") String unit);
}
