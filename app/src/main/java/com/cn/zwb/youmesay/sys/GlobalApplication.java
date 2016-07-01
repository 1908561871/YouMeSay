package com.cn.zwb.youmesay.sys;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by zhangweibo on 2016/3/21.
 */
public class GlobalApplication extends Application{


    private CrashHandler crashHandler;


    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    public void initData(){
        //异常捕获
        crashHandler=CrashHandler.getInstance();
        crashHandler.init(this);
        //图片初始化
        Fresco.initialize(getApplicationContext());
    }


}
