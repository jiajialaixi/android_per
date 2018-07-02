package com.example.wj.android_per;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.view.View;

import com.example.wj.android_per.common.persistence.FastData;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class App extends MultiDexApplication {

    protected static Context context;



    public static Context getContext() {
        return context;
    }
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        //注册存储数据的
        FastData.getInstance();
        refWatcher= setupLeakCanary();

    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        App leakApplication = (App) context.getApplicationContext();
        return leakApplication.refWatcher;
    }
}
