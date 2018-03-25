package com.artemis.githubtop.mvp.presenter;

import com.artemis.githubtop.mvp.view.ItemSearchView;

/**
 * Created by artoym on 25.03.2018.
 */

public interface ListPresenter {
    void bindItemView(ItemSearchView itemView, int position);
    void onItemClick(int position);
    int getItemCount();
}
