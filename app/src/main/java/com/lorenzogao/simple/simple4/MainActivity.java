package com.lorenzogao.simple.simple4;

import android.widget.TextView;
import android.widget.Toast;

import com.lorenzogao.simple.R;
import com.lorenzogao.simple.http.bean.MovieBean;
import com.lorenzogao.simple.simple4.base.BaseMvpActivity;
import com.lorenzogao.simple.simple4.inject.InjectPresenter;

public class MainActivity extends BaseMvpActivity implements MovieContract.MovieView {


    private TextView mTextView;

    // 多个Presenter处理

    //一个view 里面肯定会有多个Presenter 的情况 怎么处理Dagger处理  自己写一个注入


    @InjectPresenter
    private MoviePresenter mPresenter;

    @Override
    protected MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter.getMovie("02666e02030f60852248e06628a364cf", "CN");
    }

    @Override
    protected void initView() {
        mTextView = findViewById(R.id.tv_test);

    }


    @Override
    public void Loading() {
        //加载进度条
    }

    @Override
    public void onError(int error_code, String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_SHORT);
    }

    @Override
    public void onSuccess(MovieBean bean) {
        mTextView.setText(bean.toString());


    }
}
