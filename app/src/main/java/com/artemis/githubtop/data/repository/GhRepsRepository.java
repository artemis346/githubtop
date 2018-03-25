package com.artemis.githubtop.data.repository;

import com.artemis.githubtop.domain.entity.GhRepsList;
import com.artemis.githubtop.domain.entity.SearchParameters;

import io.reactivex.Observable;

/**
 * Интерфейс Репозитория для получения взаимодействия с списком объектов GitHub
 */
public interface GhRepsRepository {

    Observable<GhRepsList> getRepsList(SearchParameters params);
}
