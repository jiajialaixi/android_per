package com.example.wj.android_per.dao;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;

public class Converters {
    @TypeConverter
    public Long calendarToDatestamp(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar datestampToCalendar(Long value) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(value);
        return instance;
    }
}
