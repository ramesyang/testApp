package com.rames.testapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yangju1 on 17/3/22.
 * yangju1@staff.weibo.com
 */
public class ShutEditText extends LinearLayout implements View.OnClickListener{
    private EditText editText;
    private TextView textView;
    private View.OnClickListener listener;

    public ShutEditText(Context context) {
        super(context);
        init(context);
    }

    public ShutEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShutEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.shut_edit_text, this);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(this);
    }

    public void setInputEnable(boolean enable){
        textView.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    public void setOnEnableListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public EditText getEditText(){
        return editText;
    }

    @Override
    public void onClick(View view) {
        if(view == textView){
            listener.onClick(view);
        }
    }
}
