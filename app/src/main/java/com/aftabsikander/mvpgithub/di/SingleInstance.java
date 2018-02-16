package com.aftabsikander.mvpgithub.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by aftabsikander on 2/15/2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleInstance {
}
