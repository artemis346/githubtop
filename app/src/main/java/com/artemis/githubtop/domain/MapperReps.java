package com.artemis.githubtop.domain;

import com.artemis.githubtop.domain.entity.GhReps;
import com.artemis.githubtop.domain.entity.GhRepsList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artoym on 25.03.2018.
 */

public class MapperReps {

    public static SearchItem transform(GhReps repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final SearchItem searchItem = new SearchItem();
        searchItem.setName(repository.getName());
        searchItem.setOwnerName(repository.getOwner().getName());
        searchItem.setStarCount(repository.getStartAmmount());
        searchItem.setUrl(repository.getUrl());
        return searchItem;
    }


    public static List<SearchItem> transform(GhRepsList repositories) {
        if (repositories == null || repositories.listIsEmpty()) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        List<SearchItem> list = new ArrayList<>();
        for(GhReps rep : repositories.getItems()) {
            list.add(transform(rep));
        }
        return list;
    }
}
