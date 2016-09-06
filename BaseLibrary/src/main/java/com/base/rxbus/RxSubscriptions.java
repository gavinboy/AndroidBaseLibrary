package com.base.rxbus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/6 上午10:27
 * Email:yuanzeyao@qiyi.com
 */
public class RxSubscriptions {
    private static CompositeSubscription mSubscriptions=new CompositeSubscription();


    public static boolean isUnsubscribed(){
        return mSubscriptions.isUnsubscribed();
    }

    public static void add(Subscription s){
        if(s!=null){
            mSubscriptions.add(s);
        }
    }

    public static void remove(Subscription s){
        if(s!=null){
            mSubscriptions.remove(s);
        }
    }

    public static void clear(){
        mSubscriptions.clear();
    }

    public static void unsubscribe(){
        mSubscriptions.unsubscribe();
    }

    public static void hasSubscriptions(){
        mSubscriptions.hasSubscriptions();
    }
}
