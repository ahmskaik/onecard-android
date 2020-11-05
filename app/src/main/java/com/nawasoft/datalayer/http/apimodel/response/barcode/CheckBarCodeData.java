package com.nawasoft.datalayer.http.apimodel.response.barcode;

import com.google.gson.annotations.SerializedName;

public class CheckBarCodeData {
    @SerializedName("error")
    private String error;
    @SerializedName("memberId")
    private String memberId;

    public CheckBarCodeData(String error, String memberId) {
        this.error = error;
        this.memberId = memberId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
