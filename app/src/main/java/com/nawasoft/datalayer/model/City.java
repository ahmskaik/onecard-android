package com.nawasoft.datalayer.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("ciId")
    private String cityId;
    @SerializedName("theCityName")
    private String cityName;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("lon")
    private String longitude;

    public City(String cityId, String cityName, String latitude, String longitude) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean hasLatLang() {
        return !longitude.isEmpty() && !latitude.isEmpty();
    }

    @NonNull
    @Override
    public String toString() {
        return cityName;
    }
}
