package com.aftabsikander.mvpgithub.di.component;

import com.aftabsikander.mvpgithub.data.network.WeatherApiService;
import com.aftabsikander.mvpgithub.di.module.GlideDependencyModule;
import com.aftabsikander.mvpgithub.di.module.WeatherApiServiceModule;
import com.bumptech.glide.Glide;

import dagger.Component;

/**
 * Created by aftabsikander on 1/10/2018.
 */

@Component(modules = {WeatherApiServiceModule.class, GlideDependencyModule.class})
public interface WeatherApplicationComponent {

    Glide getGlideApp();

    WeatherApiService getWeatherApi();
}
