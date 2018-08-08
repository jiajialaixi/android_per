package com.example.wj.android_per.common.http;


import com.example.wj.android_per.bean.HomePageBean;
import com.example.wj.android_per.bean.ModelBean;
import com.example.wj.android_per.common.eventbus.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /*
     * login
     * */
    @GET("user/login")
    Observable<RequestBean<UserBean>> login(@Query("userName") String userName, @Query("passWord") String passWord);
    /*
     * 首页
     * */
    @GET("user/home")
    Observable<RequestBean<List<ModelBean>>> home();

}
