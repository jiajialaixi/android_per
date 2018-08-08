package com.example.wj.android_per.common.eventbus;



import com.example.wj.android_per.BuildConfig;
import com.example.wj.android_per.common.view.LogUtil;

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
                LogUtil.d(e);
            }
            // ignore
        }
    }

    public static void unregister(Object context) {
        try {
            EventBus.getDefault().unregister(context);
        } catch (Throwable e) {
            if (BuildConfig.DEBUG) {
                LogUtil.d(e);
            }
            // ignore
        }
    }
}
