package com.artemis.githubtop.domain.entity;

import android.text.TextUtils;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by artoym on 25.03.2018.
 */

public class SearchParameters {

    private SearchSortParams filter;
    private List<String> topics;
    private int pageNum = 1;
    private int resultSize = 20;

    public SearchSortParams getFilter() {
        return filter;
    }

    public String buildTopics() {
        return TextUtils.join("+topic:", topics);
    }

    public List<String> getTopics() {
        return topics;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getResultSize() {
        return resultSize;
    }

    public static Builder Builder() {
        return new SearchParameters().new Builder();
    }

    public class Builder {
        public Builder() {
        }

        public Builder setFilter(SearchSortParams filter) {
            SearchParameters.this.filter = filter;
            return this;
        }

        public Builder setTopics(List<String> topics) {
            SearchParameters.this.topics = topics;
            return this;
        }

        public Builder setPageNum(int pageNum) {
            SearchParameters.this.pageNum = pageNum;
            return this;
        }

        public Builder setResultSize(int resultSize) {
            SearchParameters.this.resultSize = resultSize;
            return this;
        }

        public SearchParameters create() {
            return SearchParameters.this;
        }
    }

    public enum SearchSortParams {
        Stars, Forks, Updated
    }
}
