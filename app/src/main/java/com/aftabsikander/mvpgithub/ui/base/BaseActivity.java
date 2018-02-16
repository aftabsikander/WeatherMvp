package com.aftabsikander.mvpgithub.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.aftabsikander.mvpgithub.MvpApp;
import com.aftabsikander.mvpgithub.di.component.ActivityComponent;
import com.aftabsikander.mvpgithub.di.component.DaggerActivityComponent;
import com.aftabsikander.mvpgithub.di.module.ActivityModule;
import com.aftabsikander.mvpgithub.utils.ProjectUtils;

import butterknife.Unbinder;

/**
 * Created by aftabsikander on 2/15/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback {

    private ProgressDialog progressDialog;
    private ActivityComponent activityComponent;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
    }

    //region Helper methods
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void setUnBinder(Unbinder unBinder) {
        this.unbinder = unBinder;
    }

    protected abstract void setUp();

    //endregion

    //region Interfaces override methods


    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = ProjectUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void onError(int resId) {
        ProjectUtils.showToast(this, resId, ProjectUtils.MESSAGE_LENGTH_SHORT);
    }

    @Override
    public void onError(String message) {
        ProjectUtils.showToast(this, message, ProjectUtils.MESSAGE_LENGTH_SHORT);
    }

    @Override
    public void showMessage(String message) {
        ProjectUtils.showToast(this, message, ProjectUtils.MESSAGE_LENGTH_SHORT);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ProjectUtils.showToast(this, resId, ProjectUtils.MESSAGE_LENGTH_SHORT);
    }

    @Override
    public boolean isNetworkConnected() {
        return ProjectUtils.checkNetworkStatus(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    //endregion

    //region Life cycle method

    @Override
    protected void onDestroy() {

        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
    //endregion


}
