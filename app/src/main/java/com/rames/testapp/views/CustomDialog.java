package com.rames.testapp.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.rames.testapp.R;

/**
 * Created by yangju1 on 17/5/13.
 * yangju1@staff.weibo.com
 */
public class CustomDialog extends BDialog {

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected View onInitiateView(LayoutInflater layoutInflater) {
        return null;
    }
}
