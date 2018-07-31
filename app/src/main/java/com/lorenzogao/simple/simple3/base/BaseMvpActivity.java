package com.lorenzogao.simple.simple3.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lorenzogao.simple.simple3.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 8:38
 * 邮箱：2508719070@qq.com
 * Description:
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {


    private P mPresenter;

    private List<BasePresenter> mPresenters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        mPresenters = new ArrayList<>();
        //创建P  创建只能交给子类   每个Activity 都不一样
        mPresenter = createPresenter();

        mPresenter.attach(this);

        // 1.Activity ? 2. Fragment ?   3.ViewGroup ?  抽离  工具类抽出去 或者把代买每个地方copy一份

        //这个地方要去注入  Presenter  (通过反射 Dagger)
        Field[] fields = this.getClass().getDeclaredFields();
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
                    basePresenter.attach(this);
                    //设置 注入
                    field.setAccessible(true);
                    field.set(this, basePresenter);
                    mPresenters.add(basePresenter);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        }


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
        //解绑
        for (BasePresenter presenter:mPresenters){
            presenter.detach();
        }

    }

    public P getPresenter() {
        return mPresenter;
    }


}
