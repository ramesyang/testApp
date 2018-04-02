package com.rames.testapp.views;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rames.testapp.R;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2017/8/23 下午4:06
 */
public class CustomProgressDialog extends Dialog {
    private TextView contentTV;
    private ProgressBar progressBar;
    private ImageView finishIV;
    private ImageView loadingIV;
//    private ImageView progressIV;

    public CustomProgressDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
    }

    private void init(){
        setContentView(R.layout.custom_progress_dialog);
        contentTV = (TextView) findViewById(R.id.content);
        progressBar = (ProgressBar) findViewById(R.id.loading_image);
        finishIV = (ImageView) findViewById(R.id.finish_image);
        loadingIV = (ImageView) findViewById(R.id.common_loading);
    }

    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
        finishIV.setVisibility(View.GONE);
        this.show();

        ObjectAnimator anim = ObjectAnimator.ofFloat(loadingIV, "rotation", 0f, 360f);
        anim.setDuration(10000);
        anim.start();
    }

    public void showImage(Bitmap bitmap){
        progressBar.setVisibility(View.GONE);
        finishIV.setVisibility(View.VISIBLE);
        finishIV.setImageBitmap(bitmap);
    }

    public void showImage(@NonNull int resId){
        showImage(BitmapFactory.decodeResource(getContext().getResources(), resId));
    }

    public void setContentTV(String content){
        if(!TextUtils.isEmpty(content)){
            contentTV.setText(content);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void hidden(){
        this.dismiss();
    }
}