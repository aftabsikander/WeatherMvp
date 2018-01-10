package com.aftabsikander.mvpgithub.di;

import com.aftabsikander.mvpgithub.data.network.WeatherApiService;

import dagger.Component;

/**
 * Created by aftabsikander on 1/10/2018.
 */

@Component
public interface WeatherApplicationComponent {

    WeatherApiService getWeatherApi();
}
