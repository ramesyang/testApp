package com.rames.testapp.net;

import android.content.Context;
import android.text.TextUtils;

import com.rames.testapp.BuildConfig;
import com.rames.testapp.util.Constants;
import com.rames.testapp.util.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2018/4/10 下午3:26
 */
public class RetrofitUtil {
    private static OkHttpClient okHttpClient = null;
    private static final int DEFAULT_TIMEOUT = 30;
    private static Context mContext;
    public static final String HTTP_URL = "http://gz.loongwind.com:8080/";

//    public static final OkHttpClient client = new OkHttpClient.Builder().
//            connectTimeout(60, TimeUnit.SECONDS).
//            readTimeout(60, TimeUnit.SECONDS).
//            writeTimeout(60, TimeUnit.SECONDS).build();

    public static OkHttpClient getOkHttpClientInstance(){
        if (null == okHttpClient){
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                    //设置合理的超时
                    OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                            .readTimeout(3, TimeUnit.SECONDS)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) //设置连接超时 30秒
                            .writeTimeout(3, TimeUnit.MINUTES)
//                            .addInterceptor(new MyLoggingInterceptor())//添加请求拦截
                            .retryOnConnectionFailure(true);

                    //如果不是在正式包，添加拦截 打印响应json
                    if (Constants.isRelease) {
                        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                                new HttpLoggingInterceptor.Logger() {
                                    @Override
                                    public void log(String message) {
                                        if (TextUtils.isEmpty(message)) return;
                                        String s = message.substring(0, 1);
                                        //如果收到响应是json才打印
                                        if ("{".equals(s) || "[".equals(s)) {
                                            LogUtil.e("收到响应: " + message);
                                        }
                                    }
                                });
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                        httpBuilder.addInterceptor(logging);
                    }
                    okHttpClient = httpBuilder.build();
                }
            }
        }

        return okHttpClient;
    }

    static HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            LogUtil.e(message);
        }
    });
}
