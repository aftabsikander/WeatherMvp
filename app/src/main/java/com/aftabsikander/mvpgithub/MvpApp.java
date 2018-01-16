package com.aftabsikander.mvpgithub;

import android.app.Application;

import com.aftabsikander.mvpgithub.data.DataManager;
import com.aftabsikander.mvpgithub.data.network.ApiHelper;
import com.aftabsikander.mvpgithub.di.component.ApplicationComponent;
import com.aftabsikander.mvpgithub.di.component.DaggerApplicationComponent;
import com.aftabsikander.mvpgithub.di.module.ApplicationModule;
import com.aftabsikander.mvpgithub.di.module.ContextModule;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by aftabsikander on 1/11/2018.
 */

public class MvpApp extends Application {

    @Inject
    DataManager mDataManager;

    @Inject
    ApiHelper apiHelper;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        mApplicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);


    }

    //region Helper methods for dagger global objects
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
    //endregion
}
