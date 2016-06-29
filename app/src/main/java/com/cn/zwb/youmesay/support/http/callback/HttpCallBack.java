package com.cn.zwb.youmesay.support.http.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangweibo on 2016/3/22.
 */
public abstract class HttpCallBack<T>  implements Callback <T> {




    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code()==200){
            onResponseSuccess(call,response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onResponseFailure(call);
    }

    public abstract void onResponseSuccess(Call<T> call, Response<T> response);

    public abstract  void onResponseFailure(Call<T> call);




}
