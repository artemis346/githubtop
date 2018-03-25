package com.artemis.githubtop.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Объект владельца репозитория GitHub из API GitHub
 */

public class GhRepsOwner {

    /**
     * Уникальный идентификатор пользователя GitHub
     */
    @SerializedName("id")
    private long id;

    /**
     * Псевдоним пользователя GitHub
     */
    @SerializedName("login")
    private String name;

    /**
     * Ссылка на изображение аватара пользователя GitHub
     */
    @SerializedName("avatar_url")
    private String avatarUrl;

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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
