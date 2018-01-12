package com.aftabsikander.mvpgithub.di.component;

import com.aftabsikander.mvpgithub.MvpApp;
import com.aftabsikander.mvpgithub.data.network.WeatherApiService;
import com.aftabsikander.mvpgithub.data.util.GlideAppModule;
import com.aftabsikander.mvpgithub.di.PerActivity;
import com.aftabsikander.mvpgithub.di.module.WeatherApiServiceModule;

import dagger.Component;

/**
 * Created by aftabsikander on 1/10/2018.
 */

@PerActivity
@Component(modules = {WeatherApiServiceModule.class})
public interface WeatherApplicationComponent {

    void inject(MvpApp application);

    void inject(GlideAppModule glideAppModule);

    WeatherApiService getWeatherApi();

}
