package com.aftabsikander.mvpgithub.data.network;

/**
 * Created by aftabsikander on 1/10/2018.
 */

public final class ApiConfiguration {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/";

    public static final String DAILY_FORECAST_URL = "daily?mode=json&units=metric&cnt=7";

    public static final String WEATHER_API_KEY = "15646a06818f61f7b8d7823ca833e1ce";

    public static final int READ_TIME_OUT = 180;
    public static final int CONNECTION_TIME_OUT = 180;

    private ApiConfiguration() {
        // This class is not publicly instantiable
    }
}
