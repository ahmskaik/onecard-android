package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class ActiveCardRequest extends BaseRequest {
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("serialNumber")
    private String serialNumber;
    @SerializedName("thePassword")
    private String password;

    public ActiveCardRequest(String tag, String language, String memberId, String serialNumber, String password) {
        super(tag, language);
        this.memberId = memberId;
        this.serialNumber = serialNumber;
        this.password = password;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
