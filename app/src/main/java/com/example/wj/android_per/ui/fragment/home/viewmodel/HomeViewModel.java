package com.example.wj.android_per.ui.fragment.home.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.common.http.Api;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<HomeData<List<RequestBean>>> users;



    public MutableLiveData<HomeData<List<RequestBean>>> getUser() {
        if (users == null) {
            users = new MutableLiveData<HomeData<List<RequestBean>>>();
        }
        return users;
    }

    @SuppressLint("CheckResult")
    public void getLoader() {
        HomeData homeData = new HomeData();
        Api.getApiServiceInstance().home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(request -> {
            homeData.setData(request.getResult());
            users.setValue(homeData);
        }, throwable -> {
            homeData.setThrowable(throwable);
            users.setValue(homeData);
        });

    }


    public class HomeData<T> {
        private T data;
        private Throwable throwable;

        public HomeData(T data, Throwable throwable) {
            this.data = data;
            this.throwable = throwable;
        }

        public void setData(T data) {
            this.data = data;
        }

        public HomeData() {
        }

        public T getData() {
            return data;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }
    }

}
