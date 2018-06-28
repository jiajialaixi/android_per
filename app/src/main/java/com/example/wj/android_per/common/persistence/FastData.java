package com.example.wj.android_per.common.persistence;


import com.example.wj.android_per.App;


/**
 * Created by wangJia on 2017/3/16.
 */

public class FastData extends Remember {


    private static final String HEADER_TOKEN = "login::header::token";
    private static final String SHARED_PREFS_NAME = "app.persistence";


    public static synchronized Remember getInstance() {

        return Remember.init(App.getContext(), SHARED_PREFS_NAME);
    }

    public static void setToken(String token) {
        Remember.putString(HEADER_TOKEN, token);
    }

    public static String getToken() {
        return Remember.getString(HEADER_TOKEN, "");
    }


}
