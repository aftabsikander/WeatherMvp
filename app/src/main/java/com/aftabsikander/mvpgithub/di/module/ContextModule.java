package com.aftabsikander.mvpgithub.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aftabsikander on 1/11/2018.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return context;
    }
}
