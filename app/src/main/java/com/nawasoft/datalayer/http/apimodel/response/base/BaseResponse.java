package com.nawasoft.datalayer.http.apimodel.response.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("tag")
    protected String tag;
    @SerializedName("error")
    protected boolean error;
    @SerializedName("meta")
    protected BaseMeta meta;
    @SerializedName("data")
    private BaseData<T> data;

    public BaseResponse(String tag, boolean error, BaseMeta meta, BaseData<T> data) {
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

    public boolean getError() {
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

    public boolean isError() {
        return error;
    }

    public BaseData<T> getData() {
        return data;
    }

    public void setData(BaseData<T> data) {
        this.data = data;
    }
}
