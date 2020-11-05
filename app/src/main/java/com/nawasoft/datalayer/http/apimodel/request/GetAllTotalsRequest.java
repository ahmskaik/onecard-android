package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetAllTotalsRequest extends BaseRequest {
    @SerializedName("memberId")
    private String memberId;

    public GetAllTotalsRequest(String tag, String language, String memberId) {
        super(tag, language);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
