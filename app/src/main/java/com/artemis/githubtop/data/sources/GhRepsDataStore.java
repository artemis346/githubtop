package com.artemis.githubtop.data.sources;

import com.artemis.githubtop.domain.entity.GhRepsList;

import io.reactivex.Observable;

/**
 * Интерфейс для получения списка репозиториев из источника данных
 */
public interface GhRepsDataStore {

    /**
     * Метод получения списка репозиториев, удовлетворяющего заданным параметрам
     * @param sort поле по которому осуществляется сортировка "stars", "updated", "forks"
     * @param query запрос, по которому осущетсвляется поиска подходящих репозиториев
     * @param pageNum номер страницы в поисковой выдаче
     * @param perPage количество элементов на странице
     * @return возвращается подписку на объект Списка репозиториев
     */
    Observable<GhRepsList> getGhRepsList(String sort, String query, int pageNum, int perPage);

}
