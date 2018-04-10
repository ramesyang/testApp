package com.rames.testapp;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rames.testapp.net.RetrofitUtil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangju1 on 17/5/17.
 * yangju1@staff.weibo.com
 */
public class App extends Application{
    private static App app;
    private static Retrofit retrofit;

    public final static App getApp(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        initRetrofit();
    }

    private void initRetrofit(){
        OkHttpClient client = RetrofitUtil.getOkHttpClientInstance();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();//使用 gson coverter，统一日期请求格式

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitUtil.HTTP_URL)
                .client(client) // 设置超时时间
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
