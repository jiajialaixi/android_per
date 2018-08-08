package com.example.wj.android_per.dao;




import com.example.wj.android_per.bean.BuildStringBean;


import androidx.work.Worker;

public class SeedDatabaseWorker extends Worker {

    BuildStringBean success = new BuildStringBean();
    public Result doWork() {
        Result failure;
        try {
            AppDatabase instance = AppDatabase.getInstance(getApplicationContext());
            instance.gardenPlantingDao().insert(success);
            failure = Result.SUCCESS;
        } catch (Exception ex) {
            failure = Result.FAILURE;
        }
        return failure;
    }
}
