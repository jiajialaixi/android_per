package com.example.wj.android_per.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.wj.android_per.bean.BuildStringBean;

import java.util.List;

@Dao
public interface PlantDao {


    @Query("SELECT * FROM request WHERE home_page_id = :plantId")
   BuildStringBean getPlants( String plantId );

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(BuildStringBean success);
}
