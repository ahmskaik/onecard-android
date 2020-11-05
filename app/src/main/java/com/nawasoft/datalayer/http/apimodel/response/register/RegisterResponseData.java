package com.nawasoft.datalayer.http.apimodel.response.register;

import com.google.gson.annotations.SerializedName;

public class RegisterResponseData {
    @SerializedName("memberId")
    private int memberId;

    @SerializedName("data")
    private String dataMessage;

    public RegisterResponseData(int memberId, String dataMessage) {
        this.memberId = memberId;
        this.dataMessage = dataMessage;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getDataMessage() {
        return dataMessage;
    }

    public void setDataMessage(String dataMessage) {
        this.dataMessage = dataMessage;
    }
}
