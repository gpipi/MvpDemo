package com.lorenzogao.simple.simple1.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 8:36
 * 邮箱：2508719070@qq.com
 * Description:
 */

public class BasePresenter<V extends BaseView> {


    private V mProxyView;


    private V mView;//已经解绑 可以不用这个软引用

    public void attach(V view) {
        this.mView = view;


        //动态代理
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (mView != null) {
                    return method.invoke(mView, args);
                }
                return null;
            }
        });


    }


    public void detach() {
        this.mView = null;
    }

    public V getView() {
        return mProxyView;
    }
}
