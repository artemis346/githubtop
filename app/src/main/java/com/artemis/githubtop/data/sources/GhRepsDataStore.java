package com.artemis.githubtop.data.sources;

import com.artemis.githubtop.domain.entity.GhRepsList;

import io.reactivex.Observable;

/**
 * Интерфейс для получения списка репозиториев из источника данных
 */
public interface GhRepsDataStore {

    Observable<GhRepsList> getGhRepsList(String sort, String query, int pageNum, int perPage);

}
