package com.example.wj.android_per.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wj.android_per.bean.BuildStringBean;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

@Database(entities =  BuildStringBean.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    static String DATABASE_NAME = "per-db";

    public abstract PlantDao gardenPlantingDao();

    public static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        synchronized (context) {
            if (instance == null) {
                instance = buildDatabase(context);
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(SeedDatabaseWorker.class);
                        WorkManager.getInstance().enqueue(builder.build());
                    }
                })
                .build();
    }
}
