package com.artemis.githubtop.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by artoym on 25.03.2018.
 */

public class EndlessScroller extends RecyclerView.OnScrollListener {

    private int firstVisibleItem, visibleItemCount, totalItemCount;

    private LinearLayoutManager linLayManagerTT;

    private int previousTotal = 0;

    private boolean loading = true;

    private int visibleThreshold = 18;

    private int pageNumber = 1;

    private PageScrollCallback pageCallback;

    public EndlessScroller(LinearLayoutManager linearLayoutManager, PageScrollCallback pageCallback) {
        this.linLayManagerTT = linearLayoutManager;
        this.pageCallback = pageCallback;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (pageNumber == -1) {
            return;
        }
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linLayManagerTT.getItemCount();
        firstVisibleItem = linLayManagerTT.findFirstVisibleItemPosition();
        if (loading && pageNumber > 0) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - firstVisibleItem) <= (firstVisibleItem + visibleThreshold)) {
            loading = true;
            pageNumber++;
            pageCallback.onNewPageReached();
        }

    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public interface PageScrollCallback {
        void onNewPageReached();
    }
}