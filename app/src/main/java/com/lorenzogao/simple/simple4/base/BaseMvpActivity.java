package com.lorenzogao.simple.simple4.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lorenzogao.simple.simple4.proxy.ActivityMvpProxy;
import com.lorenzogao.simple.simple4.proxy.ActivityMvpProxyImpl;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 8:38
 * 邮箱：2508719070@qq.com
 * Description:
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {


    private P mPresenter;

    private ActivityMvpProxy mMvpProxy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());


        //创建P  创建只能交给子类   每个Activity 都不一样
        mPresenter = createPresenter();

        mPresenter.attach(this);

        // 1.Activity ? 2. Fragment ?   3.ViewGroup ?  抽离  工具类抽出去 或者把代买每个地方copy一份
        mMvpProxy = createMvpProxy();

        mMvpProxy.bindAndCreatePresenter();

        initView();

        initData();


    }

    private ActivityMvpProxy createMvpProxy() {
        if (mMvpProxy == null) {
            mMvpProxy = new ActivityMvpProxyImpl<>(this);
        }
        return mMvpProxy;
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
        //解绑
        mMvpProxy.unBindCreatePresenter();

    }

    public P getPresenter() {
        return mPresenter;
    }


}
