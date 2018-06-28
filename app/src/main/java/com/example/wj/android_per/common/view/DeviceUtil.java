package com.example.wj.android_per.common.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.wj.android_per.App;


/**
 * 设备相关
 *
 * @author Guojie
 */
public class DeviceUtil {


    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) App.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return current.isAvailable();
    }






}