package com.lorenzogao.simple.simple3.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 8:36
 * 邮箱：2508719070@qq.com
 * Description:
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> {


    private V mProxyView;


    private V mView;//已经解绑 可以不用这个软引用

    //动态创建model 对象
    private M mModel;

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


        // 创建model， 动态创建  获取class 通过反射(Activity实例创建? 通过class反射创建  布局的view的对象怎么创建? 反射 )
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        try {
            // 判断一下类型
            mModel = (M) ((Class) params[1]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void detach() {

          this.mView = null;
    }

    public V getView() {
        return mProxyView;
    }

    public M getModel() {
        return mModel;
    }
}
