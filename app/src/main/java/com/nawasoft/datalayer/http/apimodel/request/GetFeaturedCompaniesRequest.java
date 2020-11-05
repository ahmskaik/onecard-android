package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetFeaturedCompaniesRequest extends BaseRequest {

    @SerializedName("countryId")
    private String countryId;
    @SerializedName("cityId")
    private String cityId;

    public GetFeaturedCompaniesRequest(String tag, String language, String countryId, String cityId) {
        super(tag, language);
        this.countryId = countryId;
        this.cityId = cityId;
    }
}
