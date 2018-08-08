package com.example.wj.android_per.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "request")
public class BuildStringBean {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "home_page_id")
    public  String home_page_id;


}
