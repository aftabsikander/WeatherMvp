package com.aftabsikander.mvpgithub.ui.base;

import com.aftabsikander.mvpgithub.R;
import com.aftabsikander.mvpgithub.data.DataManager;
import com.aftabsikander.mvpgithub.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

/**
 * Created by aftabsikander on 2/8/2018.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable;

    private V mvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    //region Getter Setter methods

    public DataManager getDataManager() {
        return dataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public V getMvpView() {
        return mvpView;
    }

    //endregion

    //region Helper methods

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    //endregion

    //region override methods
    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        mvpView = null;
    }

    @Override
    public void handleApiError(Response response) {
        if (response == null || response.errorBody() == null) {
            getMvpView().onError(R.string.api_default_error);
        }
    }

    //endregion

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
