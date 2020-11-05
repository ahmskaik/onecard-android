package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetOffersRequest extends BaseRequest {
    @SerializedName("categoryId")
    private String categoryId;
    @SerializedName("countryId")
    private String countryId;
    @SerializedName("cityId")
    private String cityId;

    public GetOffersRequest(String tag, String language, String categoryId, String countryId, String cityId) {
        super(tag, language);
        this.categoryId = categoryId;
        this.countryId = countryId;
        this.cityId = cityId;
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
