package com.rames.testapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.rames.testapp.util.LogUtil;

/**
 * Created by Administrator on 2017/4/17.
 */

public class ChatListTraslucentView extends RelativeLayout {
    private Paint mPaint;
    private int position;
    private float gradientSize = 180;
    private int mWidth;
    private int mHeight;

    public ChatListTraslucentView(@NonNull Context context) {
        super(context);
        init(context);
    }


    public ChatListTraslucentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChatListTraslucentView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private int[] mGradientColors = {0xffffffff, 0x00000000};

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        mPaint.setShader(new LinearGradient(0, 0, 0, gradientSize, mGradientColors, null, Shader.TileMode.CLAMP));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        LogUtil.e("mWidth: "+mWidth +" | mHeight: "+mHeight);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        int layerSave = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        boolean drawChild = super.drawChild(canvas, child, drawingTime);
        int offset = (mHeight - mWidth) / 2;
        int saveCount = canvas.save();
        canvas.rotate(90, mWidth / 2, mHeight / 2);
        canvas.translate(0, offset);
        canvas.drawRect(0 - offset, 0, mWidth + offset, gradientSize, mPaint);
        canvas.restoreToCount(saveCount);
        canvas.restoreToCount(layerSave);
        return drawChild;
    }

    public void setWidth(){
        gradientSize = 500;
        init(getContext());
        invalidate();
    }
}
