package com.base.rxbus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * https://github.com/YoKeyword/RxBus
 * Author:yuanzeyao
 * Date:2016/9/5 19:56
 * Email:yuanzeyao@163.com
 */
public class RxBus {
    private static volatile RxBus mInstance;
    private final Subject<Object,Object> mBus;

    private final Map<Class<?>,Object> mStickkyEventMap;

    private RxBus(){
        mBus=new SerializedSubject<>(PublishSubject.create());
        mStickkyEventMap=new ConcurrentHashMap<Class<?>,Object>();
    }

    public static RxBus getDefault(){
        if(mInstance==null){
            synchronized (RxBus.class){
                if(mInstance==null){
                    mInstance=new RxBus();
                }
            }
        }
        return mInstance;
    }


    /**
     * 发送事件
     * @param mEvent
     */
    public void post(Object mEvent){
        mBus.onNext(mEvent);
    }

    /**
     * 发送粘性事件
     * @param mEvent
     */
    public void postSticky(Object mEvent){
        mStickkyEventMap.put(mEvent.getClass(),mEvent);
        post(mEvent);
    }

    /**
     * 根据eventType的类型,返回其类型的Observable
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType){
        return mBus.ofType(eventType);
    }


    /**
     * 根据eventType的类型,返回其类型的Sticky Observable
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toStickyObservable(final Class<T> eventType){
        final Object stickyObj=mStickkyEventMap.get(eventType);
        if(stickyObj!=null){
            Observable<T> observable=mBus.ofType(eventType)
                    .mergeWith(Observable.create(new Observable.OnSubscribe<T>() {
                        @Override
                        public void call(Subscriber<? super T> subscriber) {
                            subscriber.onNext(eventType.cast(stickyObj));
                        }
                    }));
            return observable;
        }else{
            return mBus.ofType(eventType);
        }
    }


    /**
     * 根据eventType获取Sticky事件
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> T getStickyEvent(Class<T> eventType){
        if(eventType!=null){
            Object eventObj=mStickkyEventMap.get(eventType);
            if(eventObj!=null){
                T event=eventType.cast(eventObj);
                return event;
            }
            return null;
        }
        return null;
    }

    /**
     * 移除指定的eventType
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> T removeStickyEvent(Class<T> eventType){
        if(eventType!=null){
            return eventType.cast(mStickkyEventMap.get(eventType));
        }
        return null;

    }

    /**
     * 移除所有的Sticky事件
     */
    public void removeAllStickyEvents(){
        mStickkyEventMap.clear();
    }

    /**
     * 判断是否有订阅者
     * @return
     */
    public boolean hasObservers(){
        return mBus.hasObservers();
    }

    public void reset(){
        mInstance=null;
    }
}
