package com.artemis.githubtop.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.artemis.githubtop.mvp.presenter.PresenterSearch;
import com.artemis.githubtop.R;
import com.artemis.githubtop.domain.entity.GhReps;
import com.artemis.githubtop.mvp.view.SearchView;
import com.artemis.githubtop.ui.adapters.AdapterTop;
import com.artemis.githubtop.utils.ComponentUtils;
import com.artemis.githubtop.utils.EndlessScroller;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by artoym on 24.03.2018.
 */
public class TopListActivity extends MvpAppCompatActivity implements SearchView, EndlessScroller.PageScrollCallback {

    @InjectPresenter
    PresenterSearch presenter;

    @BindView(R.id.recycler_search_list)
    RecyclerView vRecyclerSearch;

    @BindView(R.id.textview_loading_error)
    TextView vTxtLoadingError;

    @BindView(R.id.progress_bar_top_list)
    ProgressBar vProgressBarFistPage;

    @BindView(R.id.progress_bar_new_page)
    ProgressBar vProgressBarNewPage;

    @BindView(R.id.button_refresh)
    ImageButton vBtnRefresh;

    AdapterTop adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_list);
        ButterKnife.bind(this);
        presenter.getNexSearchPage();
    }

    public void setAdapter() {
        adapter = new AdapterTop(presenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        vRecyclerSearch.setAdapter(adapter);
        vRecyclerSearch.setLayoutManager(layoutManager);
        vRecyclerSearch.addOnScrollListener(new EndlessScroller(layoutManager,this));
        vRecyclerSearch.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
    }

    @OnClick(R.id.button_refresh)
    void onRefreshClick() {
        presenter.reloadAllData();
    }

    @Override
    public void showLoadingPage() {
        vProgressBarNewPage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingPage() {
        vProgressBarNewPage.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingFirstPage() {
        vProgressBarFistPage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingFirstPage() {
        vProgressBarFistPage.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLabel() {
        vTxtLoadingError.setText(R.string.error_empty_search_results);
        vTxtLoadingError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorLabel() {
        vTxtLoadingError.setVisibility(View.GONE);
    }

    @Override
    public void hideRefreshButton() {
        vBtnRefresh.setVisibility(View.GONE);
    }

    @Override
    public void showRefreshButton() {
        vBtnRefresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList() {
        vRecyclerSearch.setVisibility(View.VISIBLE);
        setAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateListData() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onNextPage(int from, int itemAmount) {
        adapter.addItems(from, itemAmount);
    }

    @Override
    public void onError() {
        Toast.makeText(this, R.string.error_inet, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorInet() {
        Toast.makeText(this, R.string.error_inet_connection, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyList() {
        vRecyclerSearch.setVisibility(View.GONE);
    }

    @Override
    public void startBrowser(Uri url) {
        boolean result = ComponentUtils.startBrowserApp(this, url);
        if (!result) {
            Toast.makeText(this, R.string.error_no_browser, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNewPageReached() {
        presenter.getNexSearchPage();
    }
}
