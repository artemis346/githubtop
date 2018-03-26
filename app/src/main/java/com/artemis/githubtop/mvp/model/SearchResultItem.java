package com.artemis.githubtop.mvp.model;

/**
 * Класс результатов поиска по репозиториям GitHub
 */
public class SearchResultItem {

    /**
     * Наименование репозитория
     */
    private String name;

    /**
     * Псевдоним владельца репозитория
     */
    private String ownerName;

    /**
     * Ссылка на репозиторий
     */
    private String url;

    /**
     * Количество звезд у репозитория
     */
    private int starCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }
}
