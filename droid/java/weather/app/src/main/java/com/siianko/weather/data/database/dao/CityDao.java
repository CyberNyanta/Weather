package com.siianko.weather.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.siianko.weather.data.model.openweather.City;

import java.util.List;

/**
 * Created by yevhenii.siianko on 6/8/17.
 */
@Dao
public interface CityDao {
    @Query("SELECT * FROM cities")
    LiveData<List<City>> loadCities();

    @Query("SELECT * FROM cities where id = :cityId")
    City loadCity(int cityId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<City> cities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(City cities);
}
