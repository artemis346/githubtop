package com.artemis.githubtop.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by artoym on 24.03.2018.
 */

public class ComponentUtils {

    public static boolean startBrowserApp(Context context, Uri url) {
        if (context == null) {
            return false;
        }

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, url);
        if (browserIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(browserIntent);
            return true;
        }
        return false;
    }
}
