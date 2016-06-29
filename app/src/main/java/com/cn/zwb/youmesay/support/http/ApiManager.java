package com.cn.zwb.youmesay.support.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 * Created by zhangweibo on 2016/4/21.
 */
public abstract class ApiManager {

    //IP地址
    public static final String ENDPOINT = "http://saas.cubemmm.com";
    private static final String TOKEN = "KY0SQ3FX996PU1ZO";

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static ApiManagerService apiManager = sRetrofit.create(ApiManagerService.class);



}


