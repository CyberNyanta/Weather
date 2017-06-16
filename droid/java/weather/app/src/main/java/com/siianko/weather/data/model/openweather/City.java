package com.siianko.weather.data.model.openweather;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


@Entity(tableName = "cities")
public class City implements Serializable {

    @SerializedName("name")
    private String mName;

    @Embedded
    @SerializedName("weather")
    private List<Weather> mWeathers;

    @Embedded
    @SerializedName("main")
    private Main mMain;

    @Embedded
    @SerializedName("wind")
    private Wind mWind;

    @SerializedName("id")
    private int id;

    public City() {
    }

    public City(@NonNull String name) {
        mName = name;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        mName = name;
    }

    @Nullable
    public Weather getWeather() {
        if (mWeathers == null || mWeathers.isEmpty()) {
            return null;
        }
        return mWeathers.get(0);
    }

    public void setWeathers(@NonNull List<Weather> weathers) {
        mWeathers = weathers;
    }

    @Nullable
    public Main getMain() {
        return mMain;
    }

    public void setMain(@NonNull Main main) {
        mMain = main;
    }

    @Nullable
    public Wind getWind() {
        return mWind;
    }

    public void setWind(@NonNull Wind wind) {
        mWind = wind;
    }
}
