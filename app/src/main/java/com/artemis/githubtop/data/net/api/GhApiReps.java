package com.artemis.githubtop.data.net.api;

 import com.artemis.githubtop.domain.entity.GhRepsList;

 import io.reactivex.Observable;
 import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Интерфейс API для получения списка репозиториев
 */
public interface GhApiReps {

    @GET("/search/repositories")
    Observable<GhRepsList> searchRequest(@Query("sort") String sort, @Query("q") String query, @Query("page") int pageNum, @Query("per_page") int per_page);
}
