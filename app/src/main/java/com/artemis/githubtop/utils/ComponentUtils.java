package com.artemis.githubtop.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by artoym on 24.03.2018.
 */

public class ComponentUtils {

    public static boolean startBrowserApp(Context context, Uri url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, url);
        if (browserIntent.resolveActivity(context.getPackageManager()) != null) {
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(browserIntent);
            return true;
        }
        return false;
    }
}