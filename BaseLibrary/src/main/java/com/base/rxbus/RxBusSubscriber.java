package com.base.rxbus;

import rx.Subscriber;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/5 下午8:38
 * Email:yuanzeyao@163.com
 */
public abstract class RxBusSubscriber<T> extends Subscriber<T>{

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        try{
            onEvent(t);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void onEvent(T t);
}
