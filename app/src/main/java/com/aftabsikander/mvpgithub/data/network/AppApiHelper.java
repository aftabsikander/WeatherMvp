package com.aftabsikander.mvpgithub.data.network;

import com.aftabsikander.mvpgithub.data.network.model.weather.ForecastResult;

import retrofit2.Call;

/**
 * Created by aftabsikander on 1/10/2018.
 */

public class AppApiHelper implements ApiHelper {


    @Override
    public Call<ForecastResult> doWeatherForecastApiCall(String apiKey, String zipCode) {
        return null;
    }
}
