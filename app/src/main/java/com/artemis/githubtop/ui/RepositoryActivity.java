package com.artemis.githubtop.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.artemis.githubtop.R;
import com.artemis.githubtop.mvp.presenter.PresenterDetails;
import com.artemis.githubtop.mvp.presenter.WebViewPresenter;
import com.artemis.githubtop.mvp.view.DetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by artoym on 26.03.2018.
 */
public class RepositoryActivity extends MvpAppCompatActivity implements DetailsView {

    private static final String INTENT_URL = "url";

    @InjectPresenter
    PresenterDetails presenter;

    @BindView(R.id.webview_detail_info)
    WebView vWebViewDetails;

    @BindView(R.id.progress_bar_details)
    ProgressBar vProgress;

    @BindView(R.id.textview_error_details)
    TextView vTxtErrorDetails;

    public static void startActivity(String url, Context context) {
        Intent intent = new Intent(context, RepositoryActivity.class);
        intent.putExtra(INTENT_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reps_info);
        ButterKnife.bind(this);
        presenter.setUrl(getIntent().getStringExtra(INTENT_URL));
        initWebClient();
        presenter.startLoading();
    }

    @Override
    public void onPageError() {
        vProgress.setVisibility(View.GONE);
        vTxtErrorDetails.setVisibility(View.VISIBLE);
        vWebViewDetails.setVisibility(View.GONE);
    }

    @Override
    public void onPageSucceed() {
        vProgress.setVisibility(View.GONE);
        vTxtErrorDetails.setVisibility(View.GONE);
        vWebViewDetails.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageStart() {
        vProgress.setVisibility(View.VISIBLE);
        vTxtErrorDetails.setVisibility(View.GONE);
        vWebViewDetails.setVisibility(View.GONE);
    }

    @Override
    public void startUrlLoading(String url) {
        vWebViewDetails.loadUrl(url);
    }

    private void initWebClient() {
        vWebViewDetails.setWebViewClient(new ProgressBarWebViewClient(presenter));
    }

}
