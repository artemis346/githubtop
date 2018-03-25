package com.artemis.githubtop.data.sources;

/**
 * Класс-фабрика для создания различных источников данных
 * Если в будущем необходимо будет брать данные откуда-то еще кроме сети (кэш, база, и тп),
 * тогда здесь будут созданы соответствующие методы для создания необходимого источника, которые также будут возвращать интерфейс {@link GhRepsDataStore}.
 */

public class GhRepsStoreFactory {

    /**
     * метод создает сетевое хранилище
     * @return возвращает сетевое хранилище списка репозиториев
     */
    public GhRepsDataStore createNetStore() {
        final SearchApiProvider api = new SearchApiProvider();
        return new GhRepsNetDataStore(api);
    }
}
