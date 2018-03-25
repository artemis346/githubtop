package com.artemis.githubtop.data.net.api;

import com.artemis.githubtop.data.net.ConnectivityInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Класс для построения Http-клиентов
 */

public class GhApi {

    private static final long TIMEOUT_CONNECT = 10000;

    private static final long TIMEOUT_READ = 15000;

    private static final String BASE_URL_GITHUB = "https://api.github.com";

    public static Retrofit createClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_GITHUB)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient())
                .build();
    }

    private static OkHttpClient createHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(TIMEOUT_READ, TimeUnit.MILLISECONDS);
        httpClient.addInterceptor(new ConnectivityInterceptor());
        return httpClient.build();
    }
}
