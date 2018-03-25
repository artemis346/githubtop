package com.artemis.githubtop.domain;

import android.media.AudioAttributes;

import com.artemis.githubtop.domain.entity.GhRepsList;
import com.artemis.githubtop.data.repository.GhRepsDataRepository;
import com.artemis.githubtop.data.repository.GhRepsRepository;
import com.artemis.githubtop.data.sources.GhRepsStoreFactory;
import com.artemis.githubtop.domain.entity.SearchParameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by artoym on 24.03.2018.
 */

public class InteractorSearch {

    private final GhRepsRepository searchProvider;

    public InteractorSearch() {
        this.searchProvider = new GhRepsDataRepository(new GhRepsStoreFactory());
    }

    public Observable<GhRepsList> getTopAndroid(int pageNum) {
        SearchParameters search = SearchParameters.Builder()
                .setFilter(SearchParameters.SearchSortParams.Stars)
                .setTopics(new ArrayList<>(Collections.singletonList("android")))
                .setPageNum(pageNum)
                .setResultSize(20)
                .create();
        return searchProvider.getRepsList(search);
    }



}
