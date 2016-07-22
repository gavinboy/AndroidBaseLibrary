package com.tom.lib.sample.net;

import com.tom.lib.sample.convert.ToStringConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:yuanzeyao<br/>
 * Date:16/7/21 下午5:03
 * Email:yuanzeyao@163.com
 */
public class NetManager {
    private Retrofit mRetrofit;
    private OkHttpClient mClient;
    private static class NetManagerHolder{
        private static final NetManager NETMANAGER_INSTANCE=new NetManager();
    }

    private NetManager(){
        mClient=new OkHttpClient.Builder().build();
        mRetrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl("http://cube-server.liaohuqiu.net/")
                .addConverterFactory(new ToStringConverterFactory())
                //.addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetManager getInstance(){
        return NetManagerHolder.NETMANAGER_INSTANCE;
    }

    public APIService create(){
        return mRetrofit.create(APIService.class);
    }
}
