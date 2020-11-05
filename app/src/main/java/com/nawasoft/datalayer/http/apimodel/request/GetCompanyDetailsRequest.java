package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetCompanyDetailsRequest extends BaseRequest {
    @SerializedName("companyId")
    private String companyId;

    public GetCompanyDetailsRequest(String tag, String language, String companyId) {
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
