package com.siianko.weather.data.database.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by yevhenii.siianko on 6/8/17.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
