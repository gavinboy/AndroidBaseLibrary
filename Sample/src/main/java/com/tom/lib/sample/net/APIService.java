package com.tom.lib.sample.net;

import java.util.List;

import retrofit2.Call;

/**
 * Author:yuanzeyao<br/>
 * Date:16/7/21 下午5:17
 * Email:yuanzeyao@163.com
 */
public interface APIService {
    public Call<List<String>> getImageList();
}
