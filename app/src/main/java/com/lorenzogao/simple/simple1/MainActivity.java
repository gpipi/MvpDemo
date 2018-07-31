package com.lorenzogao.simple.simple1;

import android.widget.TextView;
import android.widget.Toast;

import com.lorenzogao.simple.R;
import com.lorenzogao.simple.simple1.base.BaseMvpActivity;
import com.lorenzogao.simple.http.bean.MovieBean;

public class MainActivity extends BaseMvpActivity<MoviePresenter> implements MovieContract.MovieView {



    private TextView mTextView;

    // 多个Presenter处理


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
        getPresenter().getMovie("02666e02030f60852248e06628a364cf", "CN");
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
