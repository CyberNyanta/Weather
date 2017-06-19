package com.siianko.weather.data.model;

/**
 * Created by yevhenii.siianko on 6/8/17.
 */

public class FirebaseCity extends BaseFirebaseModel{

    private double latitude;
    private double longitude;
    private String name;

    public FirebaseCity(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
    public FirebaseCity() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
