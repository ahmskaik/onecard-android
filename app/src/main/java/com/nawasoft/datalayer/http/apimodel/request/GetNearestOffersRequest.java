package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetNearestOffersRequest extends BaseRequest {
    @SerializedName("x")
    private String latitude;
    @SerializedName("y")
    private String longitude;
    @SerializedName("categoryId")
    private String categoryId;
    @SerializedName("countryId")
    private String countryId;
    @SerializedName("cityId")
    private String cityId;

    public GetNearestOffersRequest(String tag, String language, String latitude, String longitude, String categoryId, String countryId, String cityId) {
        super(tag, language);
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryId = categoryId;
        this.countryId = countryId;
        this.cityId = cityId;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
