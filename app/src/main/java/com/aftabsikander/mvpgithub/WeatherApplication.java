package com.aftabsikander.mvpgithub;

import android.app.Application;

import com.aftabsikander.mvpgithub.data.network.WeatherApiService;
import com.aftabsikander.mvpgithub.di.component.DaggerWeatherApplicationComponent;
import com.aftabsikander.mvpgithub.di.component.WeatherApplicationComponent;
import com.aftabsikander.mvpgithub.di.module.ContextModule;
import com.bumptech.glide.Glide;

import timber.log.Timber;

/**
 * Created by aftabsikander on 1/11/2018.
 */

public class WeatherApplication extends Application {

    private Glide glide;

    private WeatherApiService weatherApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        WeatherApplicationComponent component = DaggerWeatherApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        glide = component.getGlideApp();
        weatherApiService = component.getWeatherApi();
    }

    //region Helper methods for dagger global objects
    public Glide getGlide() {
        return glide;
    }

    public WeatherApiService getWeatherApiService() {
        return weatherApiService;
    }
    //endregion
}
