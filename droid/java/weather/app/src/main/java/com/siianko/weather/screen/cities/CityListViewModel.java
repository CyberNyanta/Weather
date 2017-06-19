package com.siianko.weather.screen.cities;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.siianko.weather.App;
import com.siianko.weather.data.database.DatabaseCreator;
import com.siianko.weather.data.database.FirebaseDatasource;
import com.siianko.weather.data.model.FirebaseCity;
import com.siianko.weather.data.model.openweather.City;

import java.util.List;

/**
 * Created by yevhenii.siianko on 6/14/17.
 */

public class CityListViewModel extends ViewModel implements FirebaseDatasource.OnChangedListener {


    private static final MutableLiveData ABSENT = new MutableLiveData();
    private final DatabaseCreator databaseCreator;

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    public static final String USERS_CHILD = "users";
    public static final String CITY_CHILD = "cities";

    private FirebaseDatasource<FirebaseCity> cityDatasourcet;

    private final LiveData<List<City>> cities;

    private CityListView view;

    public LiveData<List<City>> getCities(){
        return cities;
    }


    public CityListViewModel(){
        databaseCreator = DatabaseCreator.getInstance(App.getContext());

        LiveData<Boolean> databaseCreated = databaseCreator.isDatabaseCreated();
        cities = Transformations.switchMap(databaseCreated,
                new Function<Boolean, LiveData<List<City>>>() {
                    @Override
                    public LiveData<List<City>> apply(Boolean isDbCreated) {
                        if (!Boolean.TRUE.equals(isDbCreated)) { // Not needed here, but watch out for null
                            //noinspection unchecked
                            return ABSENT;
                        } else {
                            //noinspection ConstantConditions
                            return databaseCreator.getDatabase().cityDao().loadCities();
                        }
                    }
                });

        databaseCreator.createDb(App.getContext());
    }


    FirebaseDatasource<FirebaseCity> provideTaskDatasource(){
        Query query = FirebaseDatabase.getInstance()
                .getReference().child(USERS_CHILD)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(CITY_CHILD);
        return new FirebaseDatasource<>(query, FirebaseCity.class);
    }

    public void init(CityListView view){
        cityDatasourcet = provideTaskDatasource();
        cityDatasourcet.addOnChangedListener(this);
        this.view = view;
    }

    public void AddCity(FirebaseCity city){
        cityDatasourcet.add(city);
    }

    public void AddCity(City city){
        city.setCurrentWeather(city.getWeathers().get(0));
        databaseCreator.getDatabase().cityDao().insert(city);
    }

    //region FirebaseDatasource.OnChangedListener
    @Override
    public void onChanged(EventType type, int index, int oldIndex) {

        switch (type){
            case ADDED:
                FirebaseCity city = cityDatasourcet.get(index);
                view.loadWeather(city.getName(), city.hashCode());
                break;
            case MOVED:
                break;
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
    //endregion
}
