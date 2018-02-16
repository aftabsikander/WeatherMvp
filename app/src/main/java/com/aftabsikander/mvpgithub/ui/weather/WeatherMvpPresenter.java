package com.aftabsikander.mvpgithub.ui.weather;

import com.aftabsikander.mvpgithub.di.PerActivity;
import com.aftabsikander.mvpgithub.ui.base.MvpPresenter;

/**
 * Created by aftabsikander on 2/16/2018.
 */

@PerActivity
public interface WeatherMvpPresenter<V extends WeatherMvpView> extends MvpPresenter<V> {

    void fetechWeatherForecast(double latitude, double longitude);

    void fetchWeatherForecast();

    void validateLocationPermission();

}
