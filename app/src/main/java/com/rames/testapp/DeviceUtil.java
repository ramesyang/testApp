package com.rames.testapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by rames on 15/8/4.
 */
public class DeviceUtil {


    /**
     * The logical density of the display.
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return floating
     */
    public static float getScreenScale(Context context) {
        try {
            final float scale = context.getResources().getDisplayMetrics().density;
            return scale;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 屏幕dip值转换为像素值
     *
     * @author ramesyang
     * @date 2015-08-04
     * @param dipValue 屏幕dip值
     * @return int 屏幕像素值
     */
    public static int dip2px(Context context, float dipValue) {
        return (int) (dipValue * getScreenScale(context) + 0.5f);
    }

    /**
     * 屏幕像素值转换为dip值
     *
     * @author ramesyang
     * @date 2015-08-04
     * @param pxValue 屏幕像素值
     * @return int 屏幕dip值
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) (pxValue / getScreenDensity(context) + 0.5f);
    }

    /**
     * 获取屏幕宽度的像素值
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return int
     */
    public static int getScreenPixelsWidth(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    /**
     * 获取屏幕高度的设备独立像素值 Density-independent pixel (dp)
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return int
     */
    public static int getScreenDpHeight(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        int dpheight = (int) Math.ceil((float) height / density);
        return dpheight;
    }

    /**
     * 获取屏幕宽度的设备独立像素值 Density-independent pixel (dp)
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return int
     */
    public static int getScreenDpWidth(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int dpwidth = (int) Math.ceil((float) width / density);
        return dpwidth;
    }

    /**
     * 获取屏幕高度的像素值
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return int
     */
    public static int getScreenPixelsHeight(Context context) {
        final int height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    /**
     * 获取通知栏像素值
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return int
     */
    public static int getDimenHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;

        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {

        }

        return sbar;
    }

    /**
     * 获取设备dip
     * 设备的独立像素，一个独立像素可能对应不同数量的实际像素值 这个值可能是浮点类型的
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return floating
     */
    public static float getScreenDensity(Context context) {
        try {
            final float density = context.getResources().getDisplayMetrics().density;
            return density;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 获取设备的Dpi
     *
     * 每英寸在屏幕上的点的数量
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return int
     */
    public static int getScreenDpi(Context context) {
        try {
            final int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
            return densityDpi;
        } catch (Exception e) {
            return 160;
        }
    }

    /**
     * 获取手机状态栏高度
     *
     * @author ramesyang
     * @date 2015-08-04
     * @return int
     */
    public static int getStatusBarHeight(Activity activity) {
        //
        // 获取状态栏高度可能会存在getWindow()空指针异常
        // 此处对空指针异常进行一个捕获，一旦抛出异常，则状态栏高度返回 0。
        //
        try {
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            return frame.top;
        } catch(Exception e){
            return 0;
        }
    }

    /**获得sd卡的根目录  */
    public static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        if (isHasSDCard()) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
            return sdDir.toString();
        }

        return "";
    }

    /**检测sd卡是否存在  */
    public static boolean isHasSDCard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }

        return false;
    }
}
