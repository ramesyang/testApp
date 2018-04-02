package com.rames.testapp.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rames.testapp.R;
import com.rames.testapp.util.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yangju1 on 17/4/24.
 * yangju1@staff.weibo.com
 */
public class FocusButton extends LinearLayout implements View.OnClickListener{
    private boolean isFocus = false;
    private LinearLayout focusLayout;
    private ImageView addImage;
    private TextView focusText;
    private RelativeLayout loadingLayout;

    public FocusButton(Context context) {
        super(context);
        init();
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.focus_button_view, this);
        focusLayout = (LinearLayout) findViewById(R.id.focus_layout);
        addImage = (ImageView) findViewById(R.id.add_image);
        focusText = (TextView) findViewById(R.id.focus_text);
        loadingLayout = (RelativeLayout) findViewById(R.id.loading_layout);
        loadingLayout.setEnabled(false);
        loadingLayout.setOnClickListener(this);

        focusLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.focus_layout:
                setFocusTask();
                break;
        }
    }

    /** 设置/初始化状态 */
    public void isFocus(boolean isFocus){
        this.isFocus = isFocus;
        setStatus();
    }

    private void setStatus(){
        focusLayout.setVisibility(View.VISIBLE);
        loadingLayout.setVisibility(View.GONE);
        // 关注了,显示未关注状态
        if(isFocus){
            addImage.setVisibility(View.GONE);
            focusText.setText("取消关注");
            focusLayout.setBackgroundResource(R.drawable.focus_button_unfocus_bg);

            // 未关注,显示关注状态
        } else {
            addImage.setVisibility(View.VISIBLE);
            focusText.setText("关注");
            focusLayout.setBackgroundResource(R.drawable.focus_button_focus_bg);
        }
    }

    private void setFocusTask(){
        focusLayout.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.VISIBLE);
//        loadingLayout.setEnabled(true);
        if(isFocus){
            loadingLayout.setBackgroundResource(R.drawable.focus_button_unfocus_bg);
        }else{
            loadingLayout.setBackgroundResource(R.drawable.focus_button_focus_bg);
        }
        handler.sendEmptyMessageDelayed(isFocus ? 0 : 1, 2000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            isFocus = !isFocus;
            setStatus();
        }
    };

    @SuppressWarnings("unused")
    private int invokeTest(int a){
        return a+88;
    }

    private void invokeTest2(){
        LogUtil.e("invokeTest2");
    }

    public FocusButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FocusButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
