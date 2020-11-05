package com.nawasoft.datalayer.http.apimodel.response.barcode;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;

public class CheckBarcodeResponse {
    @SerializedName("tag")
    private String tag;
    @SerializedName("error")
    private boolean error;
    @SerializedName("meta")
    private BaseMeta meta;
    @SerializedName("data")
    private CheckBarCodeData data;

    public CheckBarcodeResponse(String tag, boolean error, BaseMeta meta, CheckBarCodeData data) {
        this.tag = tag;
        this.error = error;
        this.meta = meta;
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public BaseMeta getMeta() {
        return meta;
    }

    public void setMeta(BaseMeta meta) {
        this.meta = meta;
    }

    public CheckBarCodeData getData() {
        return data;
    }

    public void setData(CheckBarCodeData data) {
        this.data = data;
    }
}

