package com.aftabsikander.mvpgithub.di.module;

import com.aftabsikander.mvpgithub.data.network.ApiConfiguration;
import com.aftabsikander.mvpgithub.data.network.ApiHelper;
import com.aftabsikander.mvpgithub.di.SingleInstance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aftabsikander on 1/11/2018.
 */

@Module(includes = NetworkModule.class)
public class WeatherApiServiceModule {

    @Provides
    @SingleInstance
    public ApiHelper weatherApiService(Retrofit weatherRetrofit) {
        return weatherRetrofit.create(ApiHelper.class);
    }

    @Provides
    @SingleInstance
    public Gson gson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @SingleInstance
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConfiguration.BASE_URL)
                .build();
    }

}
