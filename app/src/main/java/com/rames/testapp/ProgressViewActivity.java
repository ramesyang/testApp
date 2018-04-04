package com.rames.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.rames.testapp.views.HProgressView;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2018/4/4 上午10:32
 */
public class ProgressViewActivity extends Activity{
    private Button button;
    private HProgressView progressView;
    private int count = 0;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity, ProgressViewActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_view_activity);

        button = (Button) findViewById(R.id.button);
        progressView = (HProgressView) findViewById(R.id.progress_view);

//        progressView.setProgress(0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count+=5;
                progressView.setProgress(count);
            }
        });
    }
}
