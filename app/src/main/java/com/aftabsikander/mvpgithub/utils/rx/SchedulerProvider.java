package com.aftabsikander.mvpgithub.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by aftabsikander on 2/8/2018.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
