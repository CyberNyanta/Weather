package com.siianko.weather.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siianko.weather.R;
import com.siianko.weather.data.model.openweather.City;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yevhenii.siianko on 6/16/17.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    List<? extends City> cityList = new ArrayList<>();

    @Nullable
    private final CityClickCallback cityClickCallback;

    public CityAdapter(CityClickCallback cityClickCallback) {
        this.cityClickCallback = cityClickCallback;
    }

    public void setCityList(final List<? extends City> cityList) {
        this.cityList = cityList;
        notifyDataSetChanged();

    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.town_list_item, parent, false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(cityList.get(position));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_icon)
        ImageView weatherIcon;
        @BindView(R.id.temperature_label)
        TextView temperatureLabel;
        @BindView(R.id.town_name)
        TextView cityName;

        public CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull City city) {
            cityName.setText(city.getName());
            String temp = temperatureLabel.getResources().getString(R.string.f_temperature, (int) city.getMain().getTemp());
            temperatureLabel.setText(temp);

            Picasso.with(weatherIcon.getContext()).load("http://openweathermap.org/img/w/" + city.getCurrentWeather().getIcon() + ".png").into(weatherIcon);

        }
    }
}
