package com.rames.testapp.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.rames.testapp.R;

/**
 * Created by yangju1 on 17/5/6.
 * yangju1@staff.weibo.com
 */
public class StickTopView extends LinearLayout {
    private StickTopViewItem currView;
    private StickTopViewItem newView;
    private View emptyView;
    private String score, right, content;
    private final static int ANIM_TIME = 300;

    public StickTopView(Context context) {
        super(context);
        init();
    }

    public StickTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StickTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.stick_top_view, this);
        emptyView = findViewById(R.id.empty_view);
        currView = (StickTopViewItem) findViewById(R.id.item1);
        newView = (StickTopViewItem) findViewById(R.id.item2);

        emptyView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /** 最开始时的初始化值 */
    public void setInitValue(String score, String right, String content){
        currView.setValues(score, right, content);
    }

    /** 设置值 */
    public void setValues(String score, String right, String content){
        currView.setValues(this.score, this.right, this.content);
        this.score = score;
        this.right = right;
        this.content = content;
        newView.setValues(score, right, content);

        startAnim();
    }

    private void startAnim(){
        currViewAnim();
        newViewAnim();
    }

    private void currViewAnim(){
        int height = currView.getHeight();
        ObjectAnimator tranAnim = ObjectAnimator.ofFloat(currView, "translationY", 0f, -height);
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(currView, "alpha", 1f, 0f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(tranAnim, alphaAnim);
        animSet.setDuration(ANIM_TIME);
        animSet.start();
    }

    private void newViewAnim(){
        int height = newView.getHeight();
        ObjectAnimator tranAnim = ObjectAnimator.ofFloat(newView, "translationY", 0f, -height);
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(newView, "alpha", 0f, 1f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(tranAnim, alphaAnim);
        animSet.setDuration(ANIM_TIME);
        animSet.start();
    }
}
