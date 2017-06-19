package com.siianko.weather.data.model.openweather;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "cities")
public class City implements Serializable {

    @SerializedName("name")
    private String name = "";

    @Ignore
    @SerializedName("weather")
    private List<Weather> weathers = null;

    @Embedded
    @SerializedName("main")
    private Main main = null;

    @Embedded
    @Expose
    private Weather currentWeather;

    @Embedded
    @SerializedName("wind")
    private Wind wind = null;

    @SerializedName("id")
    private int id = 0;


    @PrimaryKey(autoGenerate = true)
    private int cityId;

    public City() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(@NonNull List<Weather> weathers) {
        this.weathers = weathers;
        this.currentWeather = weathers.get(0);
    }

    @Nullable
    public Main getMain() {
        return main;
    }

    public void setMain(@NonNull Main main) {
        this.main = main;
    }

    @Nullable
    public Wind getWind() {
        return wind;
    }

    public void setWind(@NonNull Wind wind) {
        this.wind = wind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
