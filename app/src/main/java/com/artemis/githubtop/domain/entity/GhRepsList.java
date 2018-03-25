package com.artemis.githubtop.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Контейнер для списка репозиториев GitHub
 */

public class GhRepsList {

    /**
     * Список репозиториев
     */
    @SerializedName("items")
    private List<GhReps> items;

    public GhRepsList() {
        this.items = new ArrayList<>();
    }

    public List<GhReps> getItems() {
        return items;
    }

    public void setItems(List<GhReps> items) {
        this.items = items;
    }

    public boolean listIsEmpty() {
        return items == null || items.isEmpty();
    }
}
