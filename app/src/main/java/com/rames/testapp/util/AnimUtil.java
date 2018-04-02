package com.rames.testapp.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by yangju1 on 17/5/13.
 * yangju1@staff.weibo.com
 */
public class AnimUtil {

    /** 从view中间由小到大渐显 */
    public static void alphaXYShowAnim(final View view, long duration){
        view.setVisibility(View.VISIBLE);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "alpha", 0.0F, 1.0F);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 0.0f, 1.0f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 1.0f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.setInterpolator(new LinearInterpolator());
        animSet.playTogether(anim1, anim2, anim3); // 多个动画同时执行
        animSet.setDuration(duration);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
            }
        });
        animSet.start();
    }
}
