package com.siianko.weather.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.siianko.weather.data.model.openweather.Main;
import com.siianko.weather.data.model.openweather.Weather;
import com.siianko.weather.data.model.openweather.Wind;

/**
 * Created by yevhenii.siianko on 6/8/17.
 */

public interface CityModel {
    @NonNull
    String getName();

    @Nullable
    Weather getWeather() ;

    @Nullable
    Main getMain();

    Wind getWind();


}
