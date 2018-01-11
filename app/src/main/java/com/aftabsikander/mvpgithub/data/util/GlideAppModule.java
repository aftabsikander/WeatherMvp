package com.aftabsikander.mvpgithub.data.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.aftabsikander.mvpgithub.data.network.ApiConfiguration;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by aftabsikander on 1/11/2018.
 */

@GlideModule
public final class GlideAppModule extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide,
                                   @NonNull Registry registry) {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(ApiConfiguration.READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(ApiConfiguration.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .build();
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client);
        glide.getRegistry().replace(GlideUrl.class, InputStream.class, factory);
    }
}
