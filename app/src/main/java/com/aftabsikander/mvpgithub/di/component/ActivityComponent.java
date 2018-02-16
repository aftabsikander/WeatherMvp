package com.aftabsikander.mvpgithub.di.component;

import com.aftabsikander.mvpgithub.di.PerActivity;
import com.aftabsikander.mvpgithub.di.module.ActivityModule;
import com.aftabsikander.mvpgithub.ui.MainActivity;

import dagger.Component;

/**
 * Created by aftabsikander on 2/14/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}
