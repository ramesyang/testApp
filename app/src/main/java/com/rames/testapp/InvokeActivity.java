package com.rames.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rames.testapp.util.LogUtil;
import com.rames.testapp.views.FocusButton;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2017/11/29 下午4:01
 */
public class InvokeActivity extends Activity {
    private TextView textView;
    private Button button;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity, InvokeActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoke_activity);

        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun();
            }
        });
    }

    private void fun(){
        try {
            FocusButton bean = new FocusButton(this);
            Class clazz = bean.getClass();
            Field[] fields = clazz.getDeclaredFields(); // 得到当前类定义的变量
            Method[] methods = clazz.getDeclaredMethods(); // 得到当前类定义的方法
            String fieldStr = "";
            String methodStr = "";

            for (Field field : fields) {
                fieldStr += field.getName();
                fieldStr += ",";
            }

            for (Method method : methods) {
                methodStr += method.getName();
                methodStr += ",";
            }

            LogUtil.e(fieldStr);
            LogUtil.e(methodStr);

            Method m2 = clazz.getDeclaredMethod("invokeTest2");
            m2.setAccessible(true); // 加这句话可以执行私有方法
            m2.invoke(bean);

            Method m1 = clazz.getDeclaredMethod("invokeTest", int.class);
            m2.setAccessible(true);
            LogUtil.e(m1.invoke(bean, 5)+"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
