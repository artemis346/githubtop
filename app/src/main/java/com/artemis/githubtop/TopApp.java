package com.artemis.githubtop;

import android.app.Application;
import android.content.Context;

/**
 * Created by artoym on 25.03.2018.
 */

public class TopApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        setAppContext(getApplicationContext());
    }

    public static Context getContext() {
        return context;
    }

    private void setAppContext(Context cont) {
        context = cont;
    }
}
