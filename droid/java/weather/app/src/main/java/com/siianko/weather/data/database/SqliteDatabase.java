package com.siianko.weather.data.database;

/**
 * Created by yevhenii.siianko on 6/8/17.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.siianko.weather.data.database.converters.DateConverter;
import com.siianko.weather.data.database.dao.CityDao;
import com.siianko.weather.data.model.openweather.City;

@Database(entities = {City.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class SqliteDatabase extends RoomDatabase{
    static final String DATABASE_NAME = "sweather-db";

    public abstract CityDao cityDao();
}
