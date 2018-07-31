package com.lorenzogao.simple.simple4.proxy;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 16:17
 * 邮箱：2508719070@qq.com
 * Description:
 */

public interface IMvpProxy {

    void bindAndCreatePresenter(); // 一个是绑定和创建


    void unBindCreatePresenter(); // 一个解绑
}
