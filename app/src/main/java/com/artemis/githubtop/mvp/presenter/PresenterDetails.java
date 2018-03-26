package com.artemis.githubtop.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.artemis.githubtop.mvp.view.DetailsView;
import com.artemis.githubtop.mvp.view.SearchView;

/**
 * Created by artoym on 26.03.2018.
 */
@InjectViewState
public class PresenterDetails extends MvpPresenter<DetailsView> implements WebViewPresenter{

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void startLoading() {
        getViewState().onPageStart();
        getViewState().startUrlLoading(url);
    }

    @Override
    public void onLoadingStarted() {
        getViewState().onPageStart();
    }

    @Override
    public void onLoadingSucceded() {
        getViewState().onPageSucceed();
    }

    @Override
    public void onLoadingFailed() {
        getViewState().onPageError();
    }
}
