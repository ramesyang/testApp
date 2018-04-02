package com.rames.testapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.rames.testapp.views.AutofitTextView;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2017/10/23 下午6:23
 */
public class AutofitTextViewActivity extends Activity{
    private AutofitTextView autoTV;
    private EditText editText;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity, AutofitTextViewActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_fit_activity);

        autoTV = (AutofitTextView) findViewById(R.id.auto_tv);
        editText = (EditText) findViewById(R.id.edit_et);
        autoTV.setMaxTextSize(17);
        getPriceText("$181666444.99", "&99966664.00");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                getPriceText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getPriceText(String price, String rprice) {
        StringBuffer text = new StringBuffer(price);
        if(!rprice.equals("0")) {
            text.append(rprice);
            Spannable spannable = new SpannableString(text);
            spannable.setSpan(new RelativeSizeSpan(0.75f), price.length(), text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#636363")), price.length(), text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            autoTV.setText(spannable);
        }
        return;
    }
}
