package com.artemis.githubtop.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Объект слушателя на скролл для recyclerView
 */

public class EndlessScroller extends RecyclerView.OnScrollListener {

    private int firstVisibleItem, totalItemCount;

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

    public interface PageScrollCallback {
        void onNewPageReached();
    }
}