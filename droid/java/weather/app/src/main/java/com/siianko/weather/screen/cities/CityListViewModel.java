package com.siianko.weather.screen.cities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.siianko.weather.data.model.CityModel;

/**
 * Created by yevhenii.siianko on 6/14/17.
 */

public class CityListViewModel extends ViewModel {

    private MutableLiveData<CityModel> cities = new MutableLiveData<>();

    public LiveData<CityModel> getCities(){
        return cities;
    }

    public CityListViewModel(){

    }

}
