package com.aftabsikander.mvpgithub.data.network;

import com.aftabsikander.mvpgithub.data.network.model.weather.ForecastResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aftabsikander on 1/10/2018.
 */

public interface ApiHelper {

    @GET(ApiConfiguration.DAILY_FORECAST_URL)
    Call<ForecastResult> getDailyForecast(@Query("APPID") String apiKey,
                                          @Query("q") int zipCode);
}
