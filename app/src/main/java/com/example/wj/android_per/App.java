package com.example.wj.android_per;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.wj.android_per.common.persistence.FastData;
import com.squareup.leakcanary.LeakCanary;

public class App extends MultiDexApplication {

    protected static Context context;



    public static  Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

        //注册存储数据的
        FastData.getInstance();

        //检测优化
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
