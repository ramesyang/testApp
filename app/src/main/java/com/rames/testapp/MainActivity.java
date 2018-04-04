package com.rames.testapp;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rames.testapp.util.LogUtil;
import com.rames.testapp.util.NumberUtil;
import com.rames.testapp.util.StatusBarUtils;
import com.rames.testapp.util.Tips;
import com.rames.testapp.views.CustomProgressDialog;
import com.rames.testapp.views.FocusButton;
import com.rames.testapp.views.ProgressToast;
import com.rames.testapp.views.RecyclerViewGridDivider;
import com.rames.testapp.views.StickTopView;
import com.rames.testapp.views.CircleProgressView;
import com.rames.testapp.views.TestView;

import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {
    private ShutEditText shutEditText;
    private EditText editText;
    private boolean flag = false;
    private RecyclerView listView;
    private MainAdapter adapter;
    private LinearLayout dialogLayout;
    private StickTopView stickTopView;
    private Button button1, button2;
    private ImageView nextImg;
    private Button circleBtn;
    private TextView contentTV;
    private TestView testView;
    private TestReceiver testReceiver;
    public static final String TEST_BROADCAST_RECEIVER = "com.test.broadcast";
//    private ChatListTraslucentView fadeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
//        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN, WindowManager.LayoutParams. FLAG_FULLSCREEN);
        View view = this.getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(view);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.color_3c000000);
        shutEditText = (ShutEditText) findViewById(R.id.shut_editText);
        shutEditText.setInputEnable(true);
        editText = shutEditText.getEditText();
        listView = (RecyclerView) findViewById(R.id.user_recycle_view);
        dialogLayout = (LinearLayout) findViewById(R.id.dialog_layout);
        stickTopView = (StickTopView) findViewById(R.id.stick_top_view);
        nextImg = (ImageView) findViewById(R.id.next_image);
        contentTV = (TextView) findViewById(R.id.content_tv);
        testView = (TestView) findViewById(R.id.test_view);
//        fadeView = (ChatListTraslucentView) findViewById(R.id.fade_view);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button1.setText("横屏");
        button2 = (Button) findViewById(R.id.calender_btn);
        button2.setOnClickListener(this);
        button2.setText("竖屏");
        findViewById(R.id.auto_textview_btn).setOnClickListener(this);
        findViewById(R.id.invoke_btn).setOnClickListener(this);
        findViewById(R.id.progress_btn).setOnClickListener(this);

        circleBtn = (Button) findViewById(R.id.circle_progress_btn);
        circleBtn.setOnClickListener(this);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast.makeText(MainActivity.this, "falg: " + b, Toast.LENGTH_SHORT).show();
            }
        });

        shutEditText.setOnEnableListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "现在不可点击", Toast.LENGTH_SHORT).show();
            }
        });

        FocusButton focusButton = (FocusButton) findViewById(R.id.focus_button);
        focusButton.isFocus(false);

        initListView();

        valueStyle(NumberUtil.formatNumber(10022222));

        testReceiver = new TestReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(TEST_BROADCAST_RECEIVER);
        registerReceiver(testReceiver, filter);

//        View rootView = findViewById(R.id.layout);
//        ViewGroup.LayoutParams params = rootView.getLayoutParams();
//        params.height = 1200;
//        rootView.
//        rootView.setLayoutParams(params);

        linkTVFunction();

        getImageInfo();
    }

    public static View getContentView(Activity ac){
        ViewGroup view = (ViewGroup)ac.getWindow().getDecorView();
        FrameLayout content = (FrameLayout)view.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }

    private void initListView() {
        adapter = new MainAdapter();
        listView.setAdapter(adapter);
//        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void valueStyle(String content){
        SpannableString span = new SpannableString(content);
        span.setSpan(new RelativeSizeSpan(0.7f), content.length()-1, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void linkTVFunction(){
        TextView tv = (TextView) findViewById(R.id.link_tv);

        String str = "详情请点击：www.baidu.com ";
        SpannableString spanString = new SpannableString(str);
//        spanString.setSpan(new ForegroundColorSpan(Color.BLUE), str.length()-13, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new RecordClickSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this,"跳转到百度",Toast.LENGTH_SHORT).show();
            }
        }, str.length()-14, str.length()-1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        tv.setText(spanString);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void getImageInfo(){
        ExifInterface exifInterface = null;
        try {
            String path = "/storage/emulated/0/DCIM/Camera/20180402_114629.jpg";//获取图片文件的绝对路径
            ImageView tv = (ImageView) findViewById(R.id.next_image);
            tv.setImageBitmap(BitmapFactory.decodeFile(path));
            exifInterface = new ExifInterface(path);
            String datetime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);// 拍摄时间
            String deviceName = exifInterface.getAttribute(ExifInterface.TAG_MAKE);// 设备品牌
            String deviceModel = exifInterface.getAttribute(ExifInterface.TAG_MODEL); // 设备型号
            String latValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String lngValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String latRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String lngRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

            LogUtil.e("datetime:"+datetime+" | deviceName:"+deviceName+" | latRef:"+latRef+" | lngRef:"+lngRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 设置view的显示隐藏 */
    public void setShowStatus(View view, boolean status){
        if (status){
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.bottom_in);
            view.startAnimation(anim);
        }else{
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.bottom_out);
            view.startAnimation(anim);
        }
        view.setVisibility(status ? View.VISIBLE : View.GONE);
    }

    boolean isShow = false;
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
//            setShowStatus(dialogLayout, isShow);
            isShow = !isShow;

//            GravityTestActivity.show(this);

            testView.setVisibility(isShow ? View.VISIBLE : View.GONE);

        }else if(v.getId() == R.id.calender_btn){
            GravityTestActivity.show(this);
            Toast.makeText(this, "isShow:"+isShow, Toast.LENGTH_SHORT).show();

        }else if(v.getId() == R.id.circle_progress_btn){
            CircleProgressActivity.show(this);
//            final CustomProgressDialog dialog = new CustomProgressDialog(this);
//            dialog.showProgress();
//
//            circleBtn.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    dialog.showImage(R.mipmap.sns_qq_zone);
//                }
//            }, 2000);

        }else if(v.getId() == R.id.auto_textview_btn){
            AutofitTextViewActivity.show(this);
        }else if(v.getId() == R.id.invoke_btn){
            InvokeActivity.show(this);

        } else if (v.getId() == R.id.progress_btn){
            ProgressViewActivity.show(this);
        }
    }

    class TestReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
//            Tips.showTips(MainActivity.this, "收到广播了。。。。。。。"+intent.getStringExtra("info"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(testReceiver);
    }

    public class RecordClickSpan extends ClickableSpan {
        @Override
        public void onClick(View widget) {

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.colorAccent));
            ds.setUnderlineText(false);
        }
    }
}
