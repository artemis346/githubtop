package com.artemis.githubtop.mvp.presenter;

/**
 * Created by artoym on 26.03.2018.
 */

public interface WebViewPresenter {

    void startLoading();

    void onLoadingStarted();

    void onLoadingSucceded();

    void onLoadingFailed();
}
