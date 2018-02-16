package com.aftabsikander.mvpgithub.ui.base;

import retrofit2.Response;

/**
 * Created by aftabsikander on 2/8/2018.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(Response response);
}
