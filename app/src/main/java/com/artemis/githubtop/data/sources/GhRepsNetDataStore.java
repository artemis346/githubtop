package com.artemis.githubtop.data.sources;

import com.artemis.githubtop.domain.entity.GhRepsList;

import io.reactivex.Observable;

/**
 * Реализация интерфейса для получения списка репозиториев из источника сеть
 */

public class GhRepsNetDataStore implements GhRepsDataStore {

    private final SearchApiProvider apiProvider;

    GhRepsNetDataStore(SearchApiProvider apiProvider) {
        this.apiProvider = apiProvider;
    }

    @Override
    public Observable<GhRepsList> getGhRepsList(String sort, String query, int pageNum, int perPage) {
        return this.apiProvider.getGhRepsList(sort, query, pageNum, perPage);
    }
}
