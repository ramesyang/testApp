package com.rames.testapp.net;

import com.rames.testapp.BuildConfig;
import com.rames.testapp.util.Constants;
import com.rames.testapp.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2018/4/10 下午3:19
 */
public class MyLoggingInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (Constants.isRelease) {
            LogUtil.e(String.format("发送请求 %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        }
        return chain.proceed(request);
    }
}
