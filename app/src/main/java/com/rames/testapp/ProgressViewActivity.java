package com.rames.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rames.testapp.entitys.BaseEntity;
import com.rames.testapp.entitys.MihuEntity;
import com.rames.testapp.net.RetrofitUtil;
import com.rames.testapp.views.HProgressView;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2018/4/4 上午10:32
 */
public class ProgressViewActivity extends Activity {
    private Button button;
    private TextView text; // http://gz.loongwind.com:8080/mihu/get?id=1
    private HProgressView progressView;
    private int count = 0;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, ProgressViewActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_view_activity);

        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text);
        progressView = (HProgressView) findViewById(R.id.progress_view);

//        progressView.setProgress(0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 5;
                progressView.setProgress(count);

                getData2();
            }
        });
    }

    private void getData() {
        text.setText("开始请求...");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitUtil.HTTP_URL)
//                .client(RetrofitUtil.client) // 设置超时时间
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        BlogService service = retrofit.create(BlogService.class);
        Call<BaseEntity<MihuEntity>> call = service.getMihu(2);
        call.enqueue(new Callback<BaseEntity<MihuEntity>>() {
            @Override
            public void onResponse(Call<BaseEntity<MihuEntity>> call, Response<BaseEntity<MihuEntity>> response) {
                try {
                    text.setText(response.body().getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseEntity<MihuEntity>> call, Throwable t) {
                t.printStackTrace();
                text.setText("请求失败");
            }
        });
    }

    public interface BlogService {
        @GET("mihu/get")
        Call<BaseEntity<MihuEntity>> getMihu(@Query("id") int id);

        @FormUrlEncoded
        @POST("mihu/post")
        Call<BaseEntity<MihuEntity>> postMihu(@Field("id") int id);
    }

    private void getData2() {
        BlogService service = App.getApp().getRetrofit().create(BlogService.class);
        Call<BaseEntity<MihuEntity>> call = service.getMihu(2);
        call.enqueue(new Callback<BaseEntity<MihuEntity>>() {
            @Override
            public void onResponse(Call<BaseEntity<MihuEntity>> call, Response<BaseEntity<MihuEntity>> response) {
                try {
                    text.setText(response.body().getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseEntity<MihuEntity>> call, Throwable t) {
                t.printStackTrace();
                text.setText("请求失败");
            }
        });
    }

}
