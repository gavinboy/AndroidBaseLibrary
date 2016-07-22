package com.tom.lib.sample.net;

import com.google.gson.Gson;
import com.tom.lib.sample.model.ImageData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Author:yuanzeyao<br/>
 * Date:16/7/21 下午5:17
 * Email:yuanzeyao@163.com
 */
public interface APIService {
    @GET("api_demo/image-list.php")
    public Call<List<String>> getImageList();
}
