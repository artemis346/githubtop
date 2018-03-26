package com.artemis.githubtop.mvp.model;

import com.artemis.githubtop.domain.entity.GhReps;
import com.artemis.githubtop.domain.entity.GhRepsList;

import java.util.ArrayList;
import java.util.List;

/**
 * Маппер для перевода данных списка репозиториев GitHub в выдачу результатов поиска
 */

public class MapperReps {

    public static SearchResultItem transform(GhReps repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final SearchResultItem searchItem = new SearchResultItem();
        searchItem.setName(repository.getName());
        searchItem.setOwnerName(repository.getOwner().getName());
        searchItem.setStarCount(repository.getStarAmount());
        searchItem.setUrl(repository.getUrl());
        return searchItem;
    }


    public static List<SearchResultItem> transform(GhRepsList repositories) {
        if (repositories == null || repositories.listIsEmpty()) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        List<SearchResultItem> list = new ArrayList<>();
        for(GhReps rep : repositories.getItems()) {
            list.add(transform(rep));
        }
        return list;
    }
}
