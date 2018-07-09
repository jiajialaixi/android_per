package com.example.wj.android_per.ui.fragment.home.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.wj.android_per.bean.HomePageBean;
import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.common.http.Api;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<HomePageBean> users;

    public MutableLiveData<HomePageBean> getUser() {
        if (users == null) {
            users = new MutableLiveData<HomePageBean>();
        }
        return users;
    }

    @SuppressLint("CheckResult")
    public void getLoader() {
        Api.getApiServiceInstance().home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(request -> {
            users.setValue(request.getResult());
        }, throwable -> {

        });

    }


}
