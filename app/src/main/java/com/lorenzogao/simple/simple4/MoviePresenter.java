package com.lorenzogao.simple.simple4;

import android.text.TextUtils;

import com.lorenzogao.simple.http.bean.MovieBean;
import com.lorenzogao.simple.simple4.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 8:52
 * 邮箱：2508719070@qq.com
 * Description:
 */

public class MoviePresenter extends BasePresenter<MovieContract.MovieView,MovieModel> implements MovieContract.MoviePresenter {

    // 持有M和 V

    //直接new  还是? 一个Presenter对应多个model怎么决解? new 很正常，尽量分离（六大基本原则 - 接口要保持单一）
    //一般情况下1个Presenter 对应一个model 如果说你的项目有这种一对多的情况
    //一对一情况


    @Override
    public void getMovie(String key, String area) {
        // 正在加载
        getView().Loading();

        getModel().getMovie(key, area).enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                MovieBean movieBean = response.body();
                if (TextUtils.equals(movieBean.getResultcode(), "200")) {

                    //if (getView() != null)// 动态代理 就可以不用每个多判断了
                        getView().onSuccess(response.body());
                } else {
                   // if (getView() != null)
                    getView().onError(movieBean.getError_code(), movieBean.getReason());
                }
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {

            }
        });
    }
}
