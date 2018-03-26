package com.artemis.githubtop.mvp.presenter;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.artemis.githubtop.data.net.ConnectivityInterceptor;
import com.artemis.githubtop.mvp.model.MapperReps;
import com.artemis.githubtop.mvp.model.SearchResultItem;
import com.artemis.githubtop.domain.entity.GhRepsList;
import com.artemis.githubtop.domain.InteractorSearch;
import com.artemis.githubtop.mvp.view.ItemSearchView;
import com.artemis.githubtop.mvp.view.SearchView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Презентер для отображения списка репозиториев GitHub.
 * Осуществляет взаимодействие с SearchView и байндит View для элемента списка.
 */
@InjectViewState
public class PresenterSearch extends MvpPresenter<SearchView> implements ListPresenter {


    private List<SearchResultItem> items;

    private InteractorSearch topic;

    private int pageNum = 0;

    private int pageNumBeforeRefresh = 0;

    public PresenterSearch() {
        items = new ArrayList<>();
        topic = new InteractorSearch();
    }

    public void getNexSearchPage() {
        pageNum++;
        if (pageNum > 1) {
            startLoadingNewPage();
        } else {
            startLoadingFirstPage();
        }
        getData();
    }

    public void reloadAllData() {
        pageNumBeforeRefresh = pageNum;
        pageNum = 0;
        getNexSearchPage();
    }

    @Override
    public void bindItemView(ItemSearchView itemView, int position) {
        SearchResultItem rep = items.get(position);
        itemView.setRepName(rep.getName());
        itemView.setOwner(rep.getOwnerName());
        itemView.setRate(rep.getStarCount());
    }

    @Override
    public void onItemClick(int position) {
        Uri url = Uri.parse(items.get(position).getUrl());
        getViewState().startBrowser(url);
    }

    @Override
    public int getItemCount() {
        if (items != null && !items.isEmpty()) {
            return items.size();
        }
        return 0;
    }

    private void startLoadingFirstPage() {
        getViewState().hideRefreshButton();
        getViewState().showLoadingFirstPage();
        getViewState().hideErrorLabel();
    }

    private void startLoadingNewPage() {
        getViewState().showLoadingPage();
        getViewState().hideRefreshButton();
    }

    private void finishLoadingFirstPage() {
        getViewState().hideLoadingFirstPage();
        getViewState().showList();
    }

    private void finishLoadingNewPage(int previousSize, int itemsAmount) {
        getViewState().hideLoadingPage();
        getViewState().onNextPage(previousSize, itemsAmount);
    }

    private void receivedEmptyList() {
        getViewState().showRefreshButton();
        getViewState().onEmptyList();
        getViewState().hideLoadingPage();
        getViewState().hideLoadingFirstPage();
    }

    private void errorReceived() {
        if (pageNum == 1) {
            pageNum--;
            getViewState().showErrorLabel();
        }
        getViewState().hideLoadingFirstPage();
        getViewState().hideLoadingPage();
        getViewState().showRefreshButton();
    }

    private void clearListData() {
        if (items != null) {
            items.clear();
        }
        getViewState().updateListData();
    }

    private void getData() {
        topic.getTopAndroid(pageNum)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<GhRepsList, List<SearchResultItem>>() {
                    @Override
                    public List<SearchResultItem> apply(GhRepsList ghRepsList) throws Exception {
                        return MapperReps.transform(ghRepsList);
                    }
                })
                .subscribe(new Consumer<List<SearchResultItem>>() {
                               @Override
                               public void accept(List<SearchResultItem> searchItems) throws Exception {
                                   handleReceivedData(searchItems);
                               }
                           },
                        new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        resetPageNum();
                        handleErrorReceived(throwable);
                    }
                });
    }

    private void handleReceivedData(List<SearchResultItem> searchItems) {
        int sizeCurrent = 0;
        if (isFirstPage()) {
            pageNumBeforeRefresh = 0;
            clearListData();
        } else {
            sizeCurrent = items.size();
        }
        items.addAll(searchItems);

        if (items != null && !items.isEmpty()) {
            if (isFirstPage()) {
                finishLoadingFirstPage();
            } else {
                finishLoadingNewPage(sizeCurrent, searchItems.size());
            }
        } else {
            receivedEmptyList();
        }
    }

    private void handleErrorReceived(Throwable throwable) {
        resetPageNum();
        if (throwable instanceof ConnectivityInterceptor.NoConnectivityException) {
            getViewState().onErrorInet();
        } else {
            getViewState().onError();
        }
        errorReceived();
    }


    private void resetPageNum() {
        if (pageNumBeforeRefresh > 0) {
            pageNum = pageNumBeforeRefresh;
            pageNumBeforeRefresh = 0;
        }
    }

    private boolean isFirstPage() {
        return pageNum == 1;
    }
}
