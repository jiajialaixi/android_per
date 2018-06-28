package com.example.wj.android_per.common.eventbus;

import android.util.Log;


import com.example.wj.android_per.BuildConfig;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wangJia on 2017/4/18.
 */

public class EventBusDelegate {

    private static final String TAG = "EventBusDelegate";

    public static void register(Object context) {
        try {
            EventBus.getDefault().register(context);
        } catch (Throwable e) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "error", e);
            }
            // ignore
        }
    }

    public static void unregister(Object context) {
        try {
            EventBus.getDefault().unregister(context);
        } catch (Throwable e) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "error", e);
            }
            // ignore
        }
    }
}
