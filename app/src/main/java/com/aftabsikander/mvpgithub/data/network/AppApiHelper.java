package com.aftabsikander.mvpgithub.data.network;

import com.aftabsikander.mvpgithub.data.network.model.weather.ForecastResult;
import com.aftabsikander.mvpgithub.di.PerActivity;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by aftabsikander on 1/10/2018.
 */
@PerActivity
public class AppApiHelper implements ApiHelper {

    @Inject
    ApiHelper apiHelper;

    @Override
    public Call<ForecastResult> getDailyForecast(String apiKey, int zipCode) {
        return apiHelper.getDailyForecast(apiKey, zipCode);
    }
}
