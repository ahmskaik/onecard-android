package com.nawasoft.datalayer.model;

public class LatLong {
    private String latitude;
    private String longitude;

    public LatLong(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
