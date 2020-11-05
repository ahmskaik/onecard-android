package com.nawasoft.datalayer.http.apimodel.response.base;

import com.google.gson.annotations.SerializedName;

public class BaseData<T> {
    @SerializedName("error")
    private int error;

    @SerializedName("data")
    private T data;


    public BaseData(int error, T data, String dataMessage) {
        this.error = error;
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
