package com.rames.testapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by yangju1 on 16/7/23.
 * yangju1@staff.weibo.com
 */
public class HeartView extends View {
    private int offsetX = 0;
    private int offsetY = 0;

    public HeartView(Context context) {
        super(context);
        init();
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        int width = DeviceUtil.getScreenPixelsWidth(getContext());
        int height = DeviceUtil.getScreenPixelsHeight(getContext());
        offsetX = width / 2;
        offsetY = height / 2 - 55;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("------", "==onDraw==");

        getHeartPoint(20);
    }

    public Point getHeartPoint(float angle) {
        float t = (float) (angle / Math.PI);
        float x = (float) (19.5 * (16 * Math.pow(Math.sin(t), 3)));
        float y = (float) (-20 * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));
        return new Point(offsetX + (int) x, offsetY + (int) y);
    }
}
