package com.lorenzogao.simple.simple4;

import com.lorenzogao.simple.http.bean.MovieBean;
import com.lorenzogao.simple.simple4.base.BaseView;

import retrofit2.Call;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 8:51
 * 邮箱：2508719070@qq.com
 * Description: 这个类可有可无  协议类   多人开发协作    就可以先把这个写好
 */

public class MovieContract {


    interface MovieView extends BaseView {
        void Loading();

        void onError(int error_code, String reason);

        void onSuccess(MovieBean bean);

    }


    interface MoviePresenter {
        void getMovie(String key, String area);
    }

    interface MovieModel  {
        Call<MovieBean> getMovie(String key, String area) ;
    }

}
