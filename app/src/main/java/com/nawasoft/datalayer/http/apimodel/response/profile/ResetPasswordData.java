package com.nawasoft.datalayer.http.apimodel.response.profile;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordData {
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("data")
    private String message;

    public ResetPasswordData(String memberId, String message) {
        this.memberId = memberId;
        this.message = message;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
