package com.lorenzogao.simple.simple4.proxy;


import com.lorenzogao.simple.simple4.base.BasePresenter;
import com.lorenzogao.simple.simple4.base.BaseView;
import com.lorenzogao.simple.simple4.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 16:22
 * 邮箱：2508719070@qq.com
 * Description:
 */

public class MvpProxyImpl<V extends BaseView> implements IMvpProxy {

    private V mView;

    private List<BasePresenter> mPresenters;

    public MvpProxyImpl(V view) {
        //判断 是不是null  是不是接口
        this.mView = view;
        mPresenters = new ArrayList<>();
    }


    @Override
    public void bindAndCreatePresenter() {


        //这个地方要去注入  Presenter  (通过反射 Dagger)
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                // 创建注入
                Class<? extends BasePresenter> persenterClazz = (Class<? extends BasePresenter>) field.getType();
                // 判断一下类型 ? 获取继承父类，如果不是继承BasePresenter
                if (persenterClazz.getSuperclass() != BasePresenter.class) {
                    //乱七八糟的注入
                    throw new RuntimeException("no support inject presenter type!");
                }

                try {
                    //创建对象
                    BasePresenter basePresenter = persenterClazz.newInstance();
                    //并没有解绑
                    basePresenter.attach(mView);
                    //设置 注入
                    field.setAccessible(true);
                    field.set(mView, basePresenter);
                    mPresenters.add(basePresenter);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
    }

    @Override
    public void unBindCreatePresenter() {

        for (BasePresenter presenter : mPresenters) {
            presenter.detach();
        }

        this.mView=null;
    }

}
