package com.rames.testapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rames.testapp.util.ScreenSwitchUtils;

import java.util.ArrayList;

/**
 * 横竖屏切换的界面test
 * Created by yangju1 on 17/5/16.
 * yangju1@staff.weibo.com
 */
public class GravityTestActivity extends Activity implements View.OnClickListener{
    private LinearLayout topLayout;
    private RecyclerView recyclerView;
    private GravityTestAdapter adapter;
    private EditText editText;
    private Button sendBtn;
    private ScreenSwitchUtils ssUtils;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity, GravityTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gravity_test_layout);

        topLayout = (LinearLayout) findViewById(R.id.top_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        editText = (EditText) findViewById(R.id.edit_text);
        sendBtn = (Button) findViewById(R.id.send_btn);
        ssUtils = ScreenSwitchUtils.getInstance(this);
        ssUtils.start(this);

        adapter = new GravityTestAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        initData();
    }

    private void initData(){
        ArrayList<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        adapter.setData(list);
    }

    @Override
    public void onClick(View v) {
        if (v == sendBtn) {


        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String message=newConfig.orientation== Configuration.ORIENTATION_LANDSCAPE ? "屏幕设置为：横屏" : "屏幕设置为：竖屏";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /** 竖屏 */
    private void setVertical(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /** 横屏 */
    private void setHor(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
