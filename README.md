# MvpDemo
开发模式MVP
 普通的看simple1
 为什么要判断view是不是为空 有时候用户把activity finish掉了 view也回收掉了
  但是网络是延迟的  网络请求成功或者失败 用到view回掉结果
 这段代码是避免了每次用view要判断 是否为null

```

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
```


#### 1.动态创建Model
看simple2


一个presenter对应多个Model?

BasePresenter.class    通过反射动态创建

```
        // 创建model， 动态创建  获取class 通过反射(Activity实例创建? 通过class反射创建  布局的view的对
      象怎么创建? 反射 )
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        try {
            // 判断一下类型
            mModel = (M) ((Class) params[1]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

```

#### 2. 动态创建Presenter
看simple3
通过注解 然后在反射
MainActivity.class
```
    @InjectPresenter
    private MoviePresenter mPresenter;
```

InjectPresenter.class
```
@Target(ElementType.FIELD) //属性
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface InjectPresenter {
}
```

BaseMvpActivity.class  在onCreate方法下
```
 //这个地方要去注入  Presenter  (通过反射 Dagger)
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                // 创建注入
                Class<? extends BasePresenter> persenterClazz = (Class<? extends BasePresenter>)
                field.getType();
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
```

解绑处理
```
    private List<BasePresenter> mPresenters;// 用个集合把所有的Presenter的装起来
```

```
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        for (BasePresenter presenter:mPresenters){
            presenter.detach();
        }
    }
```

#### 3.静态代理封装

看simple4

