package com.artemis.githubtop.ui;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.artemis.githubtop.mvp.presenter.PresenterDetails;
import com.artemis.githubtop.mvp.presenter.WebViewPresenter;

/**
 * Created by artoym on 26.03.2018.
 */

public class ProgressBarWebViewClient extends WebViewClient {

    private WebViewPresenter presenter;

    private boolean hasError = false;

    public ProgressBarWebViewClient(WebViewPresenter pageLoading) {
        this.presenter = pageLoading;
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.cancel();
        setHasError();
        presenter.onLoadingFailed();
    }

    protected void setHasError() {
        hasError = true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        hasError = true;
        presenter.onLoadingFailed();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
        setHasError();
        presenter.onLoadingFailed();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        presenter.onLoadingStarted();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (!hasError) {
            presenter.onLoadingSucceded();
        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                presenter.onLoadingFailed();
            }
        }
    }
}
