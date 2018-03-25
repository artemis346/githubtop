package com.artemis.githubtop.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by artoym on 26.03.2018.
 */

public interface DetailsView extends MvpView {

    void onPageError();

    void onPageSucceed();

    void onPageStart();

    void startUrlLoading(String url);
}
