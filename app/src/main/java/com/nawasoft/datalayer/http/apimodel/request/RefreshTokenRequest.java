package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class RefreshTokenRequest extends BaseRequest {
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("tokenId")
    private String token;

    public RefreshTokenRequest(String tag, String language, String memberId, String token) {
        super(tag, language);
        this.memberId = memberId;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
