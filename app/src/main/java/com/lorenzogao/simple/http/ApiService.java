package com.lorenzogao.simple.http;

import com.lorenzogao.simple.http.bean.MovieBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 9:15
 * 邮箱：2508719070@qq.com
 * Description:
 */

public interface ApiService {

    @GET("boxoffice/rank")
    Call<MovieBean> getMovie(@Query("key") String key, @Query("area") String area);

}
