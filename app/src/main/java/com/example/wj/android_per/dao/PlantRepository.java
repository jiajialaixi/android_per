package com.example.wj.android_per.dao;


import android.annotation.SuppressLint;



import com.example.wj.android_per.bean.BuildStringBean;
import com.example.wj.android_per.common.view.LogUtil;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class PlantRepository {

    PlantDao plantDao;


    @SuppressLint("CheckResult")
    public BuildStringBean getPlants(String plantId) {
        return  plantDao.getPlants(plantId);
    }

    private PlantRepository instance;

    public void setPlantDao(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public PlantRepository getInstance(PlantDao plantDao) {
        synchronized(this) {
            if(instance==null){
                instance=new PlantRepository();
                instance.setPlantDao(plantDao);
            }
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public void createPlanting(BuildStringBean homePageBean) {
        Observable.just("").subscribeOn(Schedulers.io()).subscribe(s -> {
            Long insert = plantDao.insert(homePageBean);
        }, throwable -> {
            LogUtil.d(throwable);
        });


    }

}
