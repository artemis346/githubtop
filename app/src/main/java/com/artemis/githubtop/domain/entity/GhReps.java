package com.artemis.githubtop.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Объект данных об одном репозитории из API GitHub
 */

public class GhReps {

    /**
     * Уникальный идентификатор репозитория
     */
    @SerializedName("id")
    private long id;

    /**
     * Наименование репозитория GitHub
     */
    @SerializedName("name")
    private String name;

    /**
     * Полная ссылка на репозиторий GitHub
     */
    @SerializedName("html_url")
    private String url;

    /**
     * Количество звезд у репозитория GitHub
     */
    @SerializedName("stargazers_count")
    private int startAmmount;

    /**
     * Объект владельца репозитория GitHub {@link GhRepsOwner}
     */
    private GhRepsOwner owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStartAmmount() {
        return startAmmount;
    }

    public void setStartAmmount(int startAmmount) {
        this.startAmmount = startAmmount;
    }

    public GhRepsOwner getOwner() {
        return owner;
    }

    public void setOwner(GhRepsOwner owner) {
        this.owner = owner;
    }
}
