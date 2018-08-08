package com.example.wj.android_per.dao;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.wj.android_per.ui.home.fragment.home.viewmodel.HomeViewModel;


public class HomeViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    PlantRepository plantRepository;
    String plantId;
    public HomeViewModelFactory(PlantRepository plantRepository, String plantId) {
        this.plantRepository=plantRepository;
        this.plantId=plantId;
    }
    public ViewModel  create(Class modelClass ){
        return new HomeViewModel(plantRepository,plantId);
    }
}
