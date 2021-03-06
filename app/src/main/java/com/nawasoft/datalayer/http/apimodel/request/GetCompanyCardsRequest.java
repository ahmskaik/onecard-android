package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetCompanyCardsRequest extends BaseRequest {
    @SerializedName("companyId")
    private String companyId;

    public GetCompanyCardsRequest(String tag, String language, String companyId) {
        super(tag, language);
        this.companyId = companyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
