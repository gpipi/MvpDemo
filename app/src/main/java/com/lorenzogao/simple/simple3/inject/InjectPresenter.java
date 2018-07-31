package com.lorenzogao.simple.simple3.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 14:40
 * 邮箱：2508719070@qq.com
 * Description:
 */


@Target(ElementType.FIELD) //属性
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface InjectPresenter {
}
