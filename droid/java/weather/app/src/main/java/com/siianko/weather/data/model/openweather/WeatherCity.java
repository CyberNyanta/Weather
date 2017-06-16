package com.siianko.weather.data.model.openweather;

import android.support.annotation.NonNull;

public class WeatherCity {

    private final int mCityId;
    private final String mCityName;

    public WeatherCity(int cityId, @NonNull String cityName) {
        mCityId = cityId;
        mCityName = cityName;
    }

    public int getCityId() {
        return mCityId;
    }

    @NonNull
    public String getCityName() {
        return mCityName;
    }
}
