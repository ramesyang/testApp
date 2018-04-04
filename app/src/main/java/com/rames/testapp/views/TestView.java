package com.rames.testapp.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rames.testapp.R;
import com.rames.testapp.util.Tips;

/**
 * Created by yangju1 on 17/5/13.
 * yangju1@staff.weibo.com
 */
public class TestView extends View {
    private Paint mPaint = null;

    public TestView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        Tips.showTips(getContext(), "显示了onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        Tips.showTips(getContext(), "隐藏了onDetachedFromWindow");
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
//        Tips.showTips(getContext(), visibility == View.VISIBLE ? "显示了，，，" : "隐藏了...");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint background = new Paint();
        Paint line = new Paint();
        line.setStrokeWidth(4);
        background.setColor(Color.GRAY);
        line.setColor(Color.RED);

        int px = 500;
        int py = 500;

        canvas.drawRect(0, 0, px, py, background);
        canvas.save();
        canvas.rotate(180, px / 2, py / 2);
//        // 画一个向上的箭头
        canvas.drawLine(px / 2, 0, 0, py / 2, line); // 左边的斜杠
        canvas.drawLine(px / 2, 0, px, py / 2, line);// 右边的斜杠
        canvas.drawLine(px / 2, 0, px / 2, py, line);// 垂直的竖杠
//
        canvas.restore();
        canvas.translate(50, 50);
        canvas.drawCircle(px - 100, py - 100, 50, line);
    }
}
