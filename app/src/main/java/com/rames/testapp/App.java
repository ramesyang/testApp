package com.rames.testapp;

import android.app.Application;

/**
 * Created by yangju1 on 17/5/17.
 * yangju1@staff.weibo.com
 */
public class App extends Application{
    private static App app;

    public final static App getApp(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
