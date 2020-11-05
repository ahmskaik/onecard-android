package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class CheckBarcodeRequest extends BaseRequest {
    @SerializedName("serialNumber")
    private String serialNumber;

    public CheckBarcodeRequest(String tag, String language, String serialNumber) {
        super(tag, language);
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
