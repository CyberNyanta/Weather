package com.siianko.weather.data.model.openweather;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable {

    @Ignore
    @Embedded
    @SerializedName("main")
    private String mMain = "";

    @SerializedName("icon")
    private String mIcon = "";

    @NonNull
    public String getMain() {
        return mMain;
    }

    public void setMain(@NonNull String main) {
        mMain = main;
    }

    @NonNull
    public String getIcon() {
        return mIcon;
    }

    public void setIcon(@NonNull String icon) {
        mIcon = icon;
    }

   }
