package com.lorenzogao.simple.simple4.proxy;

import com.lorenzogao.simple.simple4.base.BaseView;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 16:17
 * 邮箱：2508719070@qq.com
 * Description:ActivityMvpProxy 实现
 */

public class ActivityMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements ActivityMvpProxy{


    public ActivityMvpProxyImpl(V view) {
        super(view);
    }

    // 不同对待，一般可能不会

}
