package com.aftabsikander.mvpgithub;

import android.app.Application;

import com.aftabsikander.mvpgithub.data.network.WeatherApiService;
import com.aftabsikander.mvpgithub.di.component.DaggerWeatherApplicationComponent;
import com.aftabsikander.mvpgithub.di.component.WeatherApplicationComponent;
import com.aftabsikander.mvpgithub.di.module.ContextModule;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by aftabsikander on 1/11/2018.
 */

public class MvpApp extends Application {

    @Inject
    WeatherApiService weatherApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        WeatherApplicationComponent component = DaggerWeatherApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        component.inject(this);

        WeatherApiService weatherApiService2 = component.getWeatherApi();
        WeatherApiService weatherApiService3 = component.getWeatherApi();

      /*  glide = component.getGlideApp();
        weatherApiService = component.getWeatherApi();*/
    }

    //region Helper methods for dagger global objects

    public WeatherApiService getWeatherApiService() {
        return weatherApiService;
    }
    //endregion
}
