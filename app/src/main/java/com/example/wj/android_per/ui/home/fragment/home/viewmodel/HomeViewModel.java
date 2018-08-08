package com.example.wj.android_per.ui.home.fragment.home.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.wj.android_per.bean.BuildStringBean;
import com.example.wj.android_per.bean.HomePageBean;
import com.example.wj.android_per.bean.ModelBean;
import com.example.wj.android_per.common.http.Api;
import com.example.wj.android_per.common.view.LogUtil;
import com.example.wj.android_per.dao.PlantRepository;


import java.util.List;

import io.reactivex.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<ModelBean>> users;
    PlantRepository plantRepository;
   public String  plant="";
   public String  home_page_id="";
    public HomeViewModel(PlantRepository plantRepository, String plantId) {
        this.plantRepository=plantRepository;
        this.plant=plantId;
        BuildStringBean buildStringBean= new  BuildStringBean();
        buildStringBean.home_page_id=plantId;
        plantRepository.createPlanting(buildStringBean);
    }

    public MutableLiveData<List<ModelBean>> getUser() {
        if (users == null) {
            users = new MutableLiveData<>();
        }
        return users;
    }

    @SuppressLint("CheckResult")
    public void getLoader() {

        Observable.just("").subscribeOn(Schedulers.io())
                .subscribe(s -> home_page_id = plantRepository.getPlants(plant).home_page_id, throwable -> LogUtil.d(throwable));


        Api.getApiServiceInstance().home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(request -> users.setValue(request.getResult()), throwable -> { });

    }


}
