package com.artemis.githubtop.mvp.view;

import android.net.Uri;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.artemis.githubtop.domain.entity.GhReps;

import java.util.List;

/**
 * Created by artoym on 24.03.2018.
 */

public interface SearchView extends MvpView {

    /**
     * Отображает индикатор загрузки новой страницы поиска
     */
    void showLoadingPage();

    /**
     * Скрывает индикатор загрузки новой страницы поиска
     */
    void hideLoadingPage();

    /**
     * Отображает индикатор загрузки первой страницы поиска
     */
    void showLoadingFirstPage();

    /**
     * Скрывае индикатор загрузки первой страницы поиска
     */
    void hideLoadingFirstPage();

    /**
     * Отображает Подсказку при получении пустых данных
     */
    void showErrorLabel();

    /**
     * Отображает скрывает о получении пустых данных
     */
    void hideErrorLabel();

    /**
     * Скрывает кнопку обновить контент
     */
    void hideRefreshButton();

    /**
     * Отображает кнопку обновить контент
     */
    void showRefreshButton();

    /**
     * Отображает список результатов поиска
     */
    void showList();

    /**
     * Обновляет польностью список результатов поиска
     */
    void updateListData();

    /**
     * Обновляет список элементов начиная с элемента positionFrom включительно itemAmount элементов
     * @param positionFrom номер элемента в списке, начиная с которого обновляются данные
     * @param itemAmount количество элементов, которые необходимо обновить
     */
    void onNextPage(int positionFrom, int itemAmount);

    /**
     * Отображает сообщение об ошибке загрузки данных
     */
    void onError();

    /**
     * Отобажает сообщение об ошибке подключения к сети
     */
    void onErrorInet();

    void onEmptyList();

    @StateStrategyType(SkipStrategy.class)
    void startBrowser(Uri url);
}
