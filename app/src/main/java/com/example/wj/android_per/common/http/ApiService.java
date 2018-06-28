package com.example.wj.android_per.common.http;


import com.example.wj.android_per.common.eventbus.UserBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /*
     * 售后模块  —— 售后详情
     * */
    @GET("user/login")
    Observable<RequestBean<UserBean>> login(@Query("userName") String userName, @Query("passWord") String passWord);

}
