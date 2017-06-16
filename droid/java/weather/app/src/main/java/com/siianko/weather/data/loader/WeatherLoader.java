package com.siianko.weather.data.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.siianko.weather.data.model.openweather.City;
import com.siianko.weather.data.network.ApiFactory;

import java.io.IOException;

/**
 * Created by yevhenii.siianko on 6/8/17.
 */

public class WeatherLoader extends AsyncTaskLoader<City> {

    private final String mCityName;

    public WeatherLoader(Context context, @NonNull String cityName) {
        super(context);
        mCityName = cityName;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public City loadInBackground() {
        try {
            return ApiFactory.getWeatherService().getWeather(mCityName).execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}
