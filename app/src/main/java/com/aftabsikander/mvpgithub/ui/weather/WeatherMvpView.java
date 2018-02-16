package com.aftabsikander.mvpgithub.ui.weather;

import com.aftabsikander.mvpgithub.ui.base.MvpView;

/**
 * Created by aftabsikander on 2/16/2018.
 */

public interface WeatherMvpView extends MvpView {

    void showWeatherData();

    void peformLocationPermission();
}
