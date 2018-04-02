package com.rames.testapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rames.testapp.util.LogUtil;
import com.rames.testapp.util.Tips;
import com.rames.testapp.views.CircleProgressView;

import java.util.Timer;

/**
 * Created by yangju1 on 17/6/30.
 * yangju1@staff.weibo.com
 */
public class CircleProgressActivity extends Activity implements View.OnClickListener{
    private CircleProgressView circleView;
    private ImageView image; // 礼物图片
    private ImageView bgImg; // 背景颜色图片
    private RelativeLayout giftLayout;
    private TextView countTxt; // 数量
    private Button resetBtn;
    private ViewTreeObserver vto;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity, CircleProgressActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_progress_activity);

        countTxt = (TextView) findViewById(R.id.count_txt);
        bgImg = (ImageView) findViewById(R.id.bg_img);
        giftLayout = (RelativeLayout) findViewById(R.id.gift_layout);
        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(this);
        resetBtn = (Button) findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(this);
        circleView = (CircleProgressView) findViewById(R.id.circle_progress_view);
        circleView.setOnRefreshListener(new CircleProgressView.OnRefreshListener() {
            @Override
            public void onStart() {
                LogUtil.e("onStart------");
            }

            @Override
            public void onComplete() {
                LogUtil.e("onComplete------");
            }
        });

        setViewCount(200);

        vto = countTxt.getViewTreeObserver();
        countTxt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout() {
                Tips.showTips(CircleProgressActivity.this, countTxt.isShown() ? "显示了" : "隐藏了");
            }
        });
    }

    ViewTreeObserver.OnGlobalLayoutListener viewListener = new ViewTreeObserver.OnGlobalLayoutListener(){
        @Override
        public void onGlobalLayout() {
            Tips.showTips(CircleProgressActivity.this, countTxt.isShown() ? "显示了" : "隐藏了");
        }
    };

    private void setViewCount(int count){
        countTxt.setText(count+"");
        countTxt.postDelayed(new Runnable() {
            @Override
            public void run() {
                mathParams();
                countTxt.setVisibility(View.VISIBLE);
            }
        }, 200);
    }

    private void mathParams(){
        int[] location = new int[2];
        image.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) countTxt.getLayoutParams();
        params.topMargin = -(countTxt.getHeight() / 2);
        params.rightMargin = -(countTxt.getWidth() / 2);
        countTxt.setLayoutParams(params);
        Log.e("cou---", "TextView的长高:"+countTxt.getHeight()+"|"+countTxt.getWidth()+" | image的右上角坐标:"+image.getTop()+"|"+image.getRight()
           +" | x:"+x+" | y:"+y);
//        countTxt.setLayoutParams(params);
//        countTxt.layout(0, 200, 300, 400);
//        countTxt.layout(image.getRight()-(countTxt.getWidth())/2, countTxt.getWidth(), image.getTop()+countTxt.getWidth(), image.getTop()+countTxt.getHeight());
    }

    @Override
    public void onClick(View view) {
        if(view == resetBtn){
//            countTxt.setText(""+(200+Integer.parseInt(countTxt.getText()+"")));
//            mathParams();
            if(countTxt.getVisibility() != View.VISIBLE){
                setViewCount(0);
            }else{
                countTxt.setVisibility(View.INVISIBLE);
            }

            circleView.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        circleView.destroy();
        Intent intent = new Intent();
        intent.setAction(MainActivity.TEST_BROADCAST_RECEIVER);
        intent.putExtra("info", "你大爷的大爷");
        sendBroadcast(intent);
    }
}
