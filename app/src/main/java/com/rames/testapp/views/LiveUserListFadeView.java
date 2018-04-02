package com.rames.testapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * 直播间用户列表渐变的一个遮罩
 * Created by yangju1 on 17/4/20.
 * yangju1@staff.weibo.com
 */
public class LiveUserListFadeView extends RelativeLayout {
    private Paint mPaint;
//    private floating gradientSize;
    private int mWidth;
    private int sWidth = 0;
    private int mHeight;
    private int drawWidth = 0;
    private int a = 40;
    private int gradientSize;
    private static final int DRAW_WIDTH = 60; // 画40dp宽的遮罩

    public LiveUserListFadeView(Context context) {
        super(context);
        init();
    }

    public LiveUserListFadeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LiveUserListFadeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int[] mGradientColors = {0x00000000, 0xFFffffff};
//    private int[] mGradientColors = {0xffffffff, 0x00000000};

    private void init() {
        sWidth = getScreenWidth(getContext());
        drawWidth = dp2px(getContext(), DRAW_WIDTH);
//        drawWidth = 160;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        gradientSize = 80;
        Log.e("111111------","mWidth: "+mWidth+" | mHeight: "+mHeight+" | sWidth: "+sWidth);
        mPaint.setShader(new LinearGradient(sWidth-drawWidth, 0, sWidth, mHeight, mGradientColors, null, Shader.TileMode.CLAMP));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        Log.e("000000------","mWidth: "+mWidth+" | mHeight: "+mHeight+" | sWidth: "+sWidth);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Log.e("222222------","mWidth: "+mWidth+" | mHeight: "+mHeight+" | sWidth: "+sWidth);
        int layerSave = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        boolean drawChild = super.drawChild(canvas, child, drawingTime);
        canvas.drawRect(sWidth-drawWidth, 0, sWidth, mHeight, mPaint);
        canvas.restoreToCount(layerSave);
        return drawChild;

//        int layerSave = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
//        boolean drawChild = super.drawChild(canvas, child, drawingTime);
//        int offset = (mHeight - mWidth) / 2;
//        int saveCount = canvas.save();
//        canvas.rotate(45, mWidth / 2, mHeight / 2);
//        canvas.translate(0, offset);
//        canvas.drawRect(0 - offset, 0, mWidth + offset, gradientSize, mPaint);
//        canvas.restoreToCount(saveCount);
//        canvas.restoreToCount(layerSave);
//        return drawChild;
    }

    public void setWidth(){
        a = 140;
        init();
        invalidate();
    }

    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        return display.getWidth();
    }
}
