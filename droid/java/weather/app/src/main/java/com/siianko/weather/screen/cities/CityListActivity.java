package com.siianko.weather.screen.cities;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.siianko.weather.R;
import com.siianko.weather.data.loader.RetrofitWeatherLoader;
import com.siianko.weather.data.model.FirebaseCity;
import com.siianko.weather.data.model.openweather.City;
import com.siianko.weather.screen.auth.LoginActivity;
import com.siianko.weather.screen.settings.SettingsActivity;
import com.siianko.weather.ui.CityAdapter;
import com.siianko.weather.ui.CityClickCallback;
import com.siianko.weather.util.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListActivity extends LifecycleActivity implements CityListView {
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    int SETTINGS_REQUEST_CODE = 10;
    private CityAdapter cityAdapter;
    private CityListViewModel viewModel;
    @BindView(R.id.city_list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        cityAdapter = new CityAdapter(new CityClickCallback() {
            @Override
            public void onClick(City city) {

            }
        });
        recyclerView.setAdapter(cityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        viewModel = ViewModelProviders.of(this).get(CityListViewModel.class);
        subscribeUi(viewModel);

        viewModel.init(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAutoCompleteActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.town_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            goToSettingsScreen();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ProgressDialog dialog = DialogUtils.getProgressDialog(this, null, "wait");
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                dialog.show();

                FirebaseCity city = new FirebaseCity(place.getLatLng().latitude,
                        place.getLatLng().longitude,place.getName().toString());
                viewModel.AddCity(city);

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i("TAG", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } else if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK) {
            goToLogin();
            finish();
        }
    }

    private void goToSettingsScreen() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SETTINGS_REQUEST_CODE);

    }

    private void goToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private void openAutoCompleteActivity() {

        try {

            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                    .build();

            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .setFilter(typeFilter)
                            .build(this);

            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
            int i = e.getConnectionStatusCode();
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
            DialogUtils.getOneButtonDialog(this,null,"something went wrong","ok",null);
        }
    }

    @Override
    public void loadWeather(String cityName, int id) {
        LoaderManager.LoaderCallbacks<City> callbacks = new CityWeatherCallbacks(cityName, viewModel);
        getSupportLoaderManager().initLoader(id, Bundle.EMPTY, callbacks);
    }

    private void subscribeUi(CityListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> cityList) {
                if (cityList != null) {
                    cityAdapter.setCityList(cityList);
                }
            }
        });
    }

    private class CityWeatherCallbacks implements LoaderManager.LoaderCallbacks<City> {

        private String mCityName;
        private CityListViewModel viewModel;

        public CityWeatherCallbacks(String cityName,CityListViewModel viewModel) {
            mCityName = cityName;

            this.viewModel = viewModel;
        }

        @Override
        public Loader<City> onCreateLoader(int id, Bundle args) {
            return new RetrofitWeatherLoader(CityListActivity.this, mCityName);
        }

        @Override
        public void onLoadFinished(Loader<City> loader, City city) {
            if(city != null){
                new AsyncTask<City,City,Void>(){

                    @Override
                    protected Void doInBackground(City... params) {
                        viewModel.AddCity(params[0]);
                        return null;
                    }
                }.execute(city, null, null);
            }
        }

        @Override
        public void onLoaderReset(Loader<City> loader) {
            // Do nothing
        }
    }
}
