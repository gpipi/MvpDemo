package com.lorenzogao.simple.simple2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 8:38
 * 邮箱：2508719070@qq.com
 * Description:
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {


    private  P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());


        //创建P  创建只能交给子类   每个Activity 都不一样
        mPresenter = createPresenter();

        mPresenter.attach(this);

        initView();

        initData();


    }

    protected abstract P createPresenter();


    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    public P getPresenter() {
        return mPresenter;
    }


}
