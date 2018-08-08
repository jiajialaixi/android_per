package com.example.wj.android_per.common.view;

import android.util.Log;

import com.example.wj.android_per.BuildConfig;

/*
* log 工具类  正式包不打印log
* */
public class LogUtil {

    public static void d(String msg){
        if(!BuildConfig.DEBUG){
            Log.e("per", msg);
        }

    }
    public static void d(Throwable throwable){
        if(!BuildConfig.DEBUG) {
            Log.e("per", "", throwable);
        }
    }
    public static void d(String msg,Throwable throwable){
        if(!BuildConfig.DEBUG) {
            Log.e("per", msg,throwable);
        }

    }
}
