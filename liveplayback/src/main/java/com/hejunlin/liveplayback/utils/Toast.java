package com.hejunlin.liveplayback.utils;

import android.content.Context;

/**
 * Created by HM on 2017/3/14 18:45
 */

public class Toast {

    public static void toast(Context context, String text) {
        android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT).show();
    }

}
