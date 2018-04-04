package com.rames.testapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.rames.testapp.R;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2018/4/3 下午3:23
 */
public class HProgressView extends View {
    private Paint bgPaint;
    private Paint secondPaint;
    private int maxCount = 100; // 总进度是100
    private int count = 50; // 当前进度
    private int radius = 5;
    private int width = 0;
    private int height = 0;
    private int bgColor = Color.GRAY;
    private int progressColor = Color.RED;
    //渐变数组
    private int[] arcColors = new int[]{Color.RED, Color.TRANSPARENT};

    public HProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttr(context.obtainStyledAttributes(attrs, R.styleable.HProgressView_attr));
        initView();
    }

    public HProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttr(context.obtainStyledAttributes(attrs, R.styleable.HProgressView_attr));
        initView();
    }

    public HProgressView(Context context) {
        super(context);
        initView();
    }

    private void initView(){
        initColor();
        bgPaint = initPaint();
        secondPaint = initPaint();
    }

    /** 初始化颜色 */
    private void initColor(){
        arcColors = new int[]{
                Color.parseColor("#FF9D8E"),
                Color.parseColor("#BA2509"),
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        bgPaint.setColor(bgColor);
        // 画背景
        canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, bgPaint);

        LinearGradient sweepGradient = new LinearGradient(0, 0, width, height, arcColors, null, LinearGradient.TileMode.CLAMP);
        secondPaint.setShader(sweepGradient);
        // 画进度
        canvas.drawRoundRect(new RectF(0, 0, (width/maxCount)*count, height), radius, radius, secondPaint);
    }

    public void setProgress(int progress){
        this.count = progress;
        if (count > maxCount){
            this.count = maxCount;
        }
        this.invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        radius = height/2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int dip2Px(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    private Paint initPaint(){
        //初始化画笔操作
        Paint paint = new Paint();
        // 设置是否抗锯齿
        paint.setAntiAlias(true);
        // 帮助消除锯齿
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        // 设置充满的样式
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);

        return paint;
    }

    private void getAttr(TypedArray ta){
        if (null == ta && ta.getIndexCount() < 1) return;

        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            switch (attr){
                case R.styleable.HProgressView_attr_progress_bg_color:
                    bgColor = ta.getColor(R.styleable.HProgressView_attr_progress_bg_color, Color.GRAY);

                case R.styleable.HProgressView_attr_progress_color:
                    progressColor = ta.getColor(R.styleable.HProgressView_attr_progress_color, Color.RED);

                case R.styleable.HProgressView_attr_progress:
                    count = ta.getInt(R.styleable.HProgressView_attr_progress, 0);
            }
        }

        ta.recycle();
    }
}
