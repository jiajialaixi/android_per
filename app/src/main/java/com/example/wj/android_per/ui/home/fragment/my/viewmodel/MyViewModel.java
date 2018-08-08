package com.example.wj.android_per.ui.home.fragment.my.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.wj.android_per.bean.HomePageBean;
import com.example.wj.android_per.common.http.Api;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyViewModel extends ViewModel {

    private MutableLiveData<HomePageBean> users;
    private MutableLiveData<Integer> growZoneNumber = new MutableLiveData<>();

    public MutableLiveData<HomePageBean> getUser() {
        if (users == null) {
            users = new MutableLiveData<HomePageBean>();
        }
        return users;
    }


    public final int IndexPosition=1;

    @SuppressLint("CheckResult")
    public void getLoader() {
        Api.getApiServiceInstance().home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(request -> {

          //  users.setValue(request.getResult());

        }, throwable -> {

        });
    }

}
