package com.siianko.weather.screen.cities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.siianko.weather.R;
import com.siianko.weather.data.loader.RetrofitWeatherLoader;
import com.siianko.weather.data.model.openweather.City;
import com.siianko.weather.screen.LoginActivity;
import com.siianko.weather.screen.SettingsActivity;
import com.siianko.weather.util.DialogUtils;

public class CityListActivity extends AppCompatActivity {


    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    int SETTINGS_REQUEST_CODE = 10;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                DialogUtils.getProgressDialog(this, null, "wait").show();
                Log.i("TAG", "Place: " + place.getName());
                String placse = place.getName().toString();
                LoaderManager.LoaderCallbacks<City> callbacks = new WeatherCallbacks(place.getName().toString());
                getSupportLoaderManager().initLoader(R.id.weather_loader_id, Bundle.EMPTY, callbacks);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i("TAG", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } else if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK) {
            goToLogin();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    private class WeatherCallbacks implements LoaderManager.LoaderCallbacks<City> {

        private String mCityName;

        public WeatherCallbacks(String cityName) {
            mCityName = cityName;
        }

        @Override
        public Loader<City> onCreateLoader(int id, Bundle args) {
            return new RetrofitWeatherLoader(CityListActivity.this, mCityName);
        }

        @Override
        public void onLoadFinished(Loader<City> loader, City city) {
            //showWeather(city);
            City cityr = city;
        }

        @Override
        public void onLoaderReset(Loader<City> loader) {
            // Do nothing
        }
    }
}
