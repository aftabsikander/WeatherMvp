package com.aftabsikander.mvpgithub.di.module;

import android.content.Context;

import com.aftabsikander.mvpgithub.data.network.ApiConfiguration;
import com.aftabsikander.mvpgithub.di.PerActivity;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by aftabsikander on 1/11/2018.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @PerActivity
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
    }

    @Provides
    @PerActivity
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000);// 10 MB Cache
    }

    @Provides
    @PerActivity
    public File cacheFile(Context context) {
        return new File(context.getCacheDir(), "okHttp_cache");
    }

    @Provides
    @PerActivity
    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                        Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .readTimeout(ApiConfiguration.READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(ApiConfiguration.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

}
