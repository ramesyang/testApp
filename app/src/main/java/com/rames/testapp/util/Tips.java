package com.rames.testapp.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.rames.testapp.App;

/**
 * Created by yangju1 on 17/6/28.
 * yangju1@staff.weibo.com
 */
public class Tips {
    // 浮层提示信息
    private static Toast tip_layer = null;

    /**
     * 显示浮层提示信息
     *
     * @param strResID
     */
    public static void showTips(Activity tipsShowOnActivity, int strResID) {
        showTips(tipsShowOnActivity, App.getApp().getString(strResID), Toast.LENGTH_SHORT);
    }

    public static void showTips(Activity tipsShowOnActivity, String content) {
        showTips(tipsShowOnActivity, content, Toast.LENGTH_SHORT);
    }

    public static void showLongTips(Activity tipsShowOnActivity, int strResID) {
        showTips(tipsShowOnActivity, App.getApp().getString(strResID), Toast.LENGTH_LONG);
    }

    public static void showLongTips(Activity tipsShowOnActivity, String content) {
        showTips(tipsShowOnActivity, content, Toast.LENGTH_LONG);
    }

    public static void showTips(Context context, int strResID) {
        showTips(context, App.getApp().getString(strResID), Toast.LENGTH_SHORT);
    }

    public static void showTips(Context context, String content) {
        showTips(context, content, Toast.LENGTH_SHORT);
    }

    public static void showLongTips(Context context, int strResID) {
        showTips(context, App.getApp().getString(strResID), Toast.LENGTH_LONG);
    }

    public static void showLongTips(Context context, String content) {
        showTips(context, content, Toast.LENGTH_LONG);
    }

    /**
     * 显示浮层提示信息，可传参控制显示的时间长短
     *
     * @param content 要显示的提示信息
     * @param duration 信息显示的时长 (可以选择 Toast.LENGTH_LONG 或者 Toast.LENGTH_SHORT)
     */
    public static synchronized void showTips(Activity tipsShowOnActivity, final String content, final int duration) {
        tipsShowOnActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null == tip_layer) {
                    tip_layer = Toast.makeText(App.getApp(), "", Toast.LENGTH_SHORT);
                    tip_layer.setGravity(Gravity.CENTER, 0, 0);
                }

                // 显示帮助或者提示信息的浮层
                tip_layer.setText(content);
                tip_layer.setDuration(duration);
                tip_layer.show();
            }
        });
    }

    public static synchronized void showTips(Context context, final String content, final int duration) {
        showTips((Activity)context, content, duration);
    }

    /**
     * 隐藏提示的浮层信息
     */
    public static synchronized void hiddenTips(Activity activity) {
        if (null == activity) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null != tip_layer) {
                    try {
                        tip_layer.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
