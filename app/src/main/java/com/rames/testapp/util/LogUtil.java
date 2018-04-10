package com.rames.testapp.util;

import android.provider.SyncStateContract;
import android.util.Log;

/**
 * Created by yangju1 on 17/7/10.
 * yangju1@staff.weibo.com
 */
public class LogUtil {
    private static String tag = "logUtil====";

    public static void v(String msg){
        v(tag, msg);
    }

    public static void v(String tag, String msg){
        Log.v(tag ,msg);
    }

    public static void e(String msg){
        e(tag, msg);
    }

    public static void e(String tag, String msg){
        Log.e(tag ,msg);
    }

    public static void i(String msg){
        i(tag, msg);
    }

    public static void i(String tag, String msg){
        Log.i(tag ,msg);
    }
}
