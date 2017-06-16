package com.siianko.weather.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.siianko.weather.data.model.openweather.City;

import java.util.List;

/**
 * Created by yevhenii.siianko on 6/8/17.
 */

public interface CityDao {
    @Query("SELECT * FROM comments where id = :id")
    LiveData<List<City>> loadCities(int id);

    @Query("SELECT * FROM comments where id = :id")
    List<City> loadCitySync(int productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<City> cities);
}
