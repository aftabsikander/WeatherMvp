package com.aftabsikander.mvpgithub.data;

import android.content.Context;

import com.aftabsikander.mvpgithub.data.network.ApiHelper;
import com.aftabsikander.mvpgithub.data.network.model.weather.ForecastResult;
import com.aftabsikander.mvpgithub.di.ActivityContext;
import com.aftabsikander.mvpgithub.di.ApplicationContext;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by aftabsikander on 1/16/2018.
 */

@ActivityContext
public class AppDataManager implements DataManager {

    private final Context mContext;
    private ApiHelper appApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context mContext, ApiHelper apiHelper) {
        this.mContext = mContext;
        this.appApiHelper = apiHelper;
    }

    @Override
    public Observable<ForecastResult> getDailyForecast(String apiKey, int zipCode) {
        return appApiHelper.getDailyForecast(apiKey, zipCode);
    }
}
