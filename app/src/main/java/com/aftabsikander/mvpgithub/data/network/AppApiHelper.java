package com.aftabsikander.mvpgithub.data.network;

import com.aftabsikander.mvpgithub.data.network.model.weather.ForecastResult;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by aftabsikander on 1/10/2018.
 */
@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    ApiHelper apiHelper;

    @Override
    public Observable<ForecastResult> getDailyForecast(String apiKey, int zipCode) {
        return apiHelper.getDailyForecast(apiKey, zipCode);
    }
}
