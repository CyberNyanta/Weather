package com.siianko.weather.data.model;

/**
 * Created by yevhenii.siianko on 6/8/17.
 */

public class BaseFirebaseModel {
    private String id;

    public BaseFirebaseModel(){
    }

    public BaseFirebaseModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
