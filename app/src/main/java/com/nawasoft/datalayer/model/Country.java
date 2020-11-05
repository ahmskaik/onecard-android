package com.nawasoft.datalayer.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("coId")
    private String countryId;
    @SerializedName("theName")
    private String countryName;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("lon")
    private String longitude;

    public Country(String countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    @NonNull
    @Override
    public String toString() {
        return countryName;
    }
}
