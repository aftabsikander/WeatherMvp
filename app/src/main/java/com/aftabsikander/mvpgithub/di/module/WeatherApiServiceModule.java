package com.aftabsikander.mvpgithub.di.module;

import com.aftabsikander.mvpgithub.data.network.ApiConfiguration;
import com.aftabsikander.mvpgithub.data.network.WeatherApiService;
import com.aftabsikander.mvpgithub.di.PerActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aftabsikander on 1/11/2018.
 */

@Module(includes = NetworkModule.class)
public class WeatherApiServiceModule {

    @Provides
    @PerActivity
    public WeatherApiService weatherApiService(Retrofit weatherRetrofit) {
        return weatherRetrofit.create(WeatherApiService.class);
    }

    @Provides
    @PerActivity
    public Gson gson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @PerActivity
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(ApiConfiguration.BASE_URL)
                .build();
    }
}
