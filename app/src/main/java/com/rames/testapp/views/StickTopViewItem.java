package com.rames.testapp.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rames.testapp.R;

/**
 * Created by yangju1 on 17/5/6.
 * yangju1@staff.weibo.com
 */
public class StickTopViewItem extends LinearLayout{
    private TextView scoreText, rightText, contentText;

    public StickTopViewItem(Context context) {
        super(context);
        init();
    }

    public StickTopViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StickTopViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.stick_top_view_item, this);
        scoreText = (TextView) findViewById(R.id.score_text);
        rightText = (TextView) findViewById(R.id.right_text);
        contentText = (TextView) findViewById(R.id.content_text);
    }

    public void setValues(String score, String right, String content){
        if(!TextUtils.isEmpty(score)){
            scoreText.setText(score);
        }

        if(!TextUtils.isEmpty(right)) {
            rightText.setText(right);
        }

        if(!TextUtils.isEmpty(content)) {
            contentText.setText(content);
        }
    }
}
