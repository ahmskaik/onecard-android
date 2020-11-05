package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetCitiesRequest extends BaseRequest {

    @SerializedName("countryId")
    private String countryId;

    public GetCitiesRequest(String tag, String language, String countryId) {
        super(tag, language);
        this.countryId = countryId;
    }

}
