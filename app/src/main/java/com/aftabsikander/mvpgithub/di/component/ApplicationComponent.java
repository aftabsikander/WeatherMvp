package com.aftabsikander.mvpgithub.di.component;

import android.app.Application;
import android.content.Context;

import com.aftabsikander.mvpgithub.MvpApp;
import com.aftabsikander.mvpgithub.data.DataManager;
import com.aftabsikander.mvpgithub.di.ApplicationContext;
import com.aftabsikander.mvpgithub.di.SingleInstance;
import com.aftabsikander.mvpgithub.di.module.ApplicationModule;

import dagger.Component;

/**
 * Created by aftabsikander on 1/10/2018.
 */

//@PerActivity
@SingleInstance
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MvpApp application);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

}
