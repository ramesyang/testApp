package com.rames.testapp.views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rames.testapp.DeviceUtil;
import com.rames.testapp.R;
import com.rames.testapp.util.LogUtil;
import com.rames.testapp.util.Tips;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2017/9/14 下午3:43
 */
public class GiftHitView extends LinearLayout implements View.OnClickListener{
    private TextView tv1, tv2, tv3;
    private CircleProgressView tv4;
    private boolean isExpand = false; // 是否已经展开
    private final static int ANIM_DURATION = 1000;
    private final static int RADIUS = 500; // 半径长度200
    private final static int ANGLE = 45; // 与中间tv2的度数，因为tv2一定与x轴或y轴垂直
    private int screenW, screenH; // 屏幕的宽和高
    private RelativeLayout layout;
    private boolean isAnimationing = false; // 是否在动画中

    public GiftHitView(Context context) {
        super(context);
        init();
    }

    public GiftHitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.gift_hit_view, this);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (CircleProgressView) findViewById(R.id.tv4);
        layout = (RelativeLayout) findViewById(R.id.layout);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        screenW = DeviceUtil.getScreenPixelsWidth(getContext());
        screenH = DeviceUtil.getScreenPixelsHeight(getContext());
    }

    private void animStart1(int startX, int endX, int startY, int endY){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(tv1, "translationX", startX, endX);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tv1, "translationY", startY, endY);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(tv1, "alpha", isExpand?1:0, isExpand?0:1);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(tv1, "scaleX", isExpand?1:0, isExpand?0:1);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(tv1, "scaleY", isExpand?1:0, isExpand?0:1);
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(anim1, anim2, anim3, anim4, anim5);
        animSet.setDuration(ANIM_DURATION);
        animSet.setStartDelay(isExpand ? 500 : 0);
        animSet.start();
    }

    private void animStart2(int startX, int endX, int startY, int endY){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(tv2, "translationX", startX, endX);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tv2, "translationY", startY, endY);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(tv2, "alpha", isExpand?1:0, isExpand?0:1);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(tv2, "scaleX", isExpand?1:0, isExpand?0:1);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(tv2, "scaleY", isExpand?1:0, isExpand?0:1);
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(anim1, anim2, anim3, anim4, anim5);
        animSet.setDuration(ANIM_DURATION);
        animSet.setStartDelay(isExpand ? 250 : 250);
        animSet.start();
    }

    private void animStart3(int startX, int endX, int startY, int endY){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(tv3, "translationX", startX, endX);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tv3, "translationY", startY, endY);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(tv3, "alpha", isExpand?1:0, isExpand?0:1);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(tv3, "scaleX", isExpand?1:0, isExpand?0:1);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(tv3, "scaleY", isExpand?1:0, isExpand?0:1);
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(anim1, anim2, anim3, anim4, anim5);
        animSet.setDuration(ANIM_DURATION);
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimationing = true;}

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimationing = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animSet.setStartDelay(isExpand ? 0 : 500);
        animSet.start();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv1){
            Tips.showTips(getContext(), "66");

        }else if(v.getId() == R.id.tv2){
            Tips.showTips(getContext(), "520| sss:"+DeviceUtil.getScreenScale(getContext()));

        }else if(v.getId() == R.id.tv3){
            Tips.showTips(getContext(), "1314");

        }else if(v.getId() == R.id.tv4){
            int[] xy = mathXY(ANGLE);
            int x = xy[0];
            int y = xy[1];
            View view = tv4;
            view = this;
//            LogUtil.e("getTop(): "+view.getTop() + " | getLeft(): "+view.getLeft()+ " | getRight(): "+view.getRight()+ " | getBottom(): "+view.getBottom());
            LogUtil.e("x: "+x+" y: "+y);

            getScreenLocation(view);
            if(!isAnimationing) {
                inBottom1();
            }

//            inLeft(x, y);

//            inRight(x, y);
        }
    }

    private void getScreenLocation(View view){
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        LogUtil.e("locX: "+x +" |locY: "+y);
    }

    /** 外置礼物 */
    private void inBottom(int x, int y){
        if(isExpand) {
            animStart1(-x, 0, -y, 0);
            animStart2(0, 0, -RADIUS, 0);
            animStart3(x, 0, -y, 0);
        }else{
            animStart1(0, -x, 0, -y);
            animStart2(0, 0, 0, -RADIUS);
            animStart3(0, x, 0, -y);
        }
        isExpand = !isExpand;
    }

    /** 外置礼物 */
    private void inBottom1(){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        params.width = mathXY(20)[0]+Math.abs(mathXY(110)[0])+DeviceUtil.dip2px(getContext(), 40);
        params.height = RADIUS+DeviceUtil.dip2px(getContext(), 32);
        layout.setLayoutParams(params);

        RelativeLayout.LayoutParams viewParams = (RelativeLayout.LayoutParams) tv4.getLayoutParams();
        viewParams.rightMargin = Math.abs(mathXY(110)[0])+DeviceUtil.dip2px(getContext(), 8);
        tv4.setLayoutParams(viewParams);

        if(isExpand) {
            animStart1(-mathXY(20)[0], 0, -mathXY(20)[1], 0);
            animStart2(-mathXY(65)[0], 0, -mathXY(65)[1], 0);
            animStart3(-mathXY(110)[0], 0, -mathXY(110)[1], 0);
        }else{
            animStart1(0, -mathXY(20)[0], 0, -mathXY(20)[1]);
            animStart2(0, -mathXY(65)[0], 0, -mathXY(65)[1]);
            animStart3(0, -mathXY(110)[0], 0, -mathXY(110)[1]);
        }
        isExpand = !isExpand;
    }

    /** 在左边的时候 */
    private void inLeft(int x, int y){
        if(isExpand) {
            animStart1(0, 0, RADIUS, 0);
            animStart2(x, 0, y, 0);
            animStart3(RADIUS, 0, 0, 0);
        }else{
            animStart1(0, 0, 0, RADIUS);
            animStart2(0, x, 0, y);
            animStart3(0, RADIUS, 0, 0);
        }
        isExpand = !isExpand;
    }

    /** 在左边的时候 */
    private void inRight(int x, int y){
        if(isExpand) {
            animStart1(-RADIUS, 0, 0, 0);
            animStart2(-x, 0, y, 0);
            animStart3(0, 0, RADIUS, 0);
        }else{
            animStart1(0, -RADIUS, 0, 0);
            animStart2(0, -x, 0, y);
            animStart3(0, 0, 0, RADIUS);
        }
        isExpand = !isExpand;
    }

    /**
     * 利用角度和斜边长得到两条直角边长
     * @param du 角度
     * @return 两条边长的数组
     */
    private static int[] mathXY(double du){
        double x = Math.cos(du/180*Math.PI) * RADIUS;
        double y = Math.sin(du/180*Math.PI) * RADIUS;
        LogUtil.e("radius:"+RADIUS+" |du:"+du+" |x:"+x+" |y:"+y);
        return new int[]{(int)x, (int)y};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
