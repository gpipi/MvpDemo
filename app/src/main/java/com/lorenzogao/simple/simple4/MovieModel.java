package com.lorenzogao.simple.simple4;

import android.util.Log;

import com.lorenzogao.simple.http.ApiService;
import com.lorenzogao.simple.http.bean.MovieBean;
import com.lorenzogao.simple.simple4.base.BaseModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 8:54
 * 邮箱：2508719070@qq.com
 * Description:
 */

public class MovieModel extends BaseModel implements MovieContract.MovieModel {


    @Override
    public Call<MovieBean> getMovie(String key, String area) {


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/")
                .client(client)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求的Url地址
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<MovieBean> movice = service.getMovie(key, area);


        return movice;
    }
}
