package com.artemis.githubtop.data.sources;

import com.artemis.githubtop.data.net.api.GhApi;
import com.artemis.githubtop.domain.entity.GhRepsList;
import com.artemis.githubtop.data.net.api.GhApiReps;

import io.reactivex.Observable;

/**
 * Реализация API для получения списка источников из сети
 */
public class SearchApiProvider implements GhRepsDataStore {

    @Override
    public Observable<GhRepsList> getGhRepsList(String sort, String query, int pageNum, int perPage) {
        GhApiReps chattingService = GhApi.createClient().create(GhApiReps.class);
        return chattingService.searchRequest(sort, query, pageNum, perPage);
    }
}
