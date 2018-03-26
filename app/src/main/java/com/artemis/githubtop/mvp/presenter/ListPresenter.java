package com.artemis.githubtop.mvp.presenter;

import com.artemis.githubtop.mvp.view.ItemSearchView;

/**
 * Интерфейс для презентора управляющего отображением списка
 */

public interface ListPresenter {
    void bindItemView(ItemSearchView itemView, int position);
    void onItemClick(int position);
    int getItemCount();
}
