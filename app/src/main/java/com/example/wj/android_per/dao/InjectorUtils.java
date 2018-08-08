package com.example.wj.android_per.dao;

import android.content.Context;


public class InjectorUtils {

    public static PlantRepository getPlantRepository(Context context){

        return new PlantRepository().getInstance(AppDatabase.getInstance(context).gardenPlantingDao());
    }

   public static HomeViewModelFactory provideHomeViewModelFactory(Context context, String plantId)
    {
        PlantRepository plantRepository = getPlantRepository(context);
        return  new HomeViewModelFactory(plantRepository,plantId);
    }
}
