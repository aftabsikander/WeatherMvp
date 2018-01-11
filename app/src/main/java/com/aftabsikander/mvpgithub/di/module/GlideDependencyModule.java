package com.aftabsikander.mvpgithub.di.module;

import android.content.Context;

import com.aftabsikander.mvpgithub.data.util.GlideApp;
import com.bumptech.glide.Glide;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aftabsikander on 1/11/2018.
 */

@Module(includes = ContextModule.class)
public class GlideDependencyModule {

    @Provides
    public Glide glideApp(Context context) {
        return GlideApp.get(context);
    }
}
