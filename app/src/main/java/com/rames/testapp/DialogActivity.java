package com.rames.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by yangju1 on 16/7/18.
 * yangju1@staff.weibo.com
 */
public class DialogActivity extends Activity {
    LinearLayout mLayout;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity, DialogActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mLayout = (LinearLayout) findViewById(R.id.layout);

        for (int i = 0; i < 5; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_item, null);
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
            params.leftMargin = getMargin();
            layout.setLayoutParams(params);
            mLayout.addView(view);
        }
    }

    private int getMargin(){
        int imageW = DeviceUtil.dip2px(this, 57); // image的宽度为57dp
        float w1 = DeviceUtil.getScreenPixelsWidth(this) - (5*imageW);
        float marginWidth = w1 / (5 + 1); // 屏幕宽度
        return (int)marginWidth;
    }
}
