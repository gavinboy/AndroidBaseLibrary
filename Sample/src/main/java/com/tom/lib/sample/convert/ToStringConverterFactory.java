


/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tom.lib.sample.convert;


        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.lang.annotation.Annotation;
        import java.lang.reflect.Type;
        import java.util.ArrayList;
        import java.util.List;

        import okhttp3.MediaType;
        import okhttp3.RequestBody;
        import okhttp3.ResponseBody;
        import retrofit2.Converter;
        import retrofit2.Retrofit;

public class ToStringConverterFactory extends Converter.Factory {
    static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        Log.d("yzy","response:"+type.toString());
        Log.d("yzy","response:"+(List.class.equals(type)));
        if (String.class.equals(type)) {
            return new Converter<ResponseBody, String>() {
                @Override public String convert(ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        }else{
            return new Converter<ResponseBody, List<String>>() {
                @Override
                public List<String> convert(ResponseBody value) throws IOException {
                    Log.d("yzy","response-->convert...");
                    List<String> urls=new ArrayList<String>();
                    try {
                        JSONObject json=new JSONObject(value.string());
                        JSONObject data=json.optJSONObject("data");
                        JSONArray list=data.optJSONArray("list");

                        for(int i=0;i<list.length();i++){
                            JSONObject obj=list.getJSONObject(i);
                            String url=obj.optString("pic");
                            urls.add(url);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("yzy","list size:"+urls.size());
                    return urls;
                }
            };
        }
        //return null;
    }

    @Override public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                                    Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        Log.d("yzy","request:"+type.toString());
        if (String.class.equals(type)) {
            return new Converter<String, RequestBody>() {
                @Override public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value);
                }
            };
        }
        return null;
    }
}