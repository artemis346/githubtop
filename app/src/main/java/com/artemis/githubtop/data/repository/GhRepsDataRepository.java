package com.artemis.githubtop.data.repository;

import com.artemis.githubtop.domain.entity.GhRepsList;
import com.artemis.githubtop.data.sources.GhRepsStoreFactory;
import com.artemis.githubtop.domain.entity.SearchParameters;

import io.reactivex.Observable;

/**
 * Реализация {@link GhRepsRepository} для объединения логик загрузки данных из разных источников
 */
public class GhRepsDataRepository implements GhRepsRepository {

    private final GhRepsStoreFactory factory;

    public GhRepsDataRepository(GhRepsStoreFactory factory) {
        this.factory = factory;
    }

    @Override
    public Observable<GhRepsList> getRepsList(SearchParameters searchParameters) {
        return factory.createNetStore().getGhRepsList(searchParameters.getFilter().toString().toLowerCase(),
                searchParameters.buildTopics(),
                searchParameters.getPageNum(),
                searchParameters.getResultSize());
    }
}
