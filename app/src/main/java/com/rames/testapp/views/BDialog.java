package com.rames.testapp.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.rames.testapp.R;

/**
 * 弹出菜单的 dialog
 *
 * @author Administrator
 */
public class BDialog extends Dialog {
    private LinearLayout layout;

    public BDialog(Context context) {
        super(context, R.style.CustomDialog);
        initiate(context);
    }

    public BDialog(Context context, int theme) {
        super(context, theme);
        initiate(context);
    }

    protected BDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initiate(context);
    }

    private void initiate(Context context) {
        View mRootView = LayoutInflater.from(context).inflate(R.layout.base_dialog_bg, null);
        layout = (LinearLayout) mRootView.findViewById(R.id.layout);
        setContentView(layout);
    }

    public LinearLayout getLayout(){
        layout.removeAllViews();
        return layout;
    }

    /**
     * @return The view for #setContentView
     */
//    protected abstract View onInitiateView(LayoutInflater layoutInflater);


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(mRootView);
//    }

    /**
     * 初始化 dialog
     */
    public void setDialogSize(float widthP) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels * widthP);
        window.setAttributes(lp);
    }


    @Override
    public void show() {
        if (getContext() instanceof Activity && ((Activity) getContext()).isFinishing()) {
            return;
        }
        super.show();
    }
}
