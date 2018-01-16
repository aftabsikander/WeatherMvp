package com.aftabsikander.mvpgithub.di.module;

import android.app.Application;
import android.content.Context;

import com.aftabsikander.mvpgithub.data.AppDataManager;
import com.aftabsikander.mvpgithub.data.DataManager;
import com.aftabsikander.mvpgithub.di.ApplicationContext;
import com.aftabsikander.mvpgithub.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aftabsikander on 1/16/2018.
 */

@Module(includes = WeatherApiServiceModule.class)
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PerActivity
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}
