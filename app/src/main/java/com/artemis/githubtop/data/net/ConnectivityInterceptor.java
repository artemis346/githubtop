package com.artemis.githubtop.data.net;

import com.artemis.githubtop.TopApp;
import com.artemis.githubtop.utils.NetUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Перехватчик запросов для отлавлниявания ошибки отсутствия соеднинения
 */
public class ConnectivityInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetUtils.isThereInternetConnection(TopApp.getContext())) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

    public class NoConnectivityException extends IOException {
        @Override
        public String getMessage() {
            return "There is not internet connection";
        }
    }
}
