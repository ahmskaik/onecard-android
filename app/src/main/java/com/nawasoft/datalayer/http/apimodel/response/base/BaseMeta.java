package com.nawasoft.datalayer.http.apimodel.response.base;

import com.google.gson.annotations.SerializedName;

public class BaseMeta {
    @SerializedName("status")
    private int status;
    @SerializedName("response")
    private String response;
    @SerializedName("message")
    private String message;
    @SerializedName("statusNumber")
    private int statusNumber;
    @SerializedName("page")
    private int page;
    @SerializedName("numberOfPage")
    private int numberOfPage;

    public BaseMeta(int status, String response, String message, int statusNumber, int page, int numberOfPage) {
        this.status = status;
        this.response = response;
        this.message = message;
        this.statusNumber = statusNumber;
        this.page = page;
        this.numberOfPage = numberOfPage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusNumber() {
        return statusNumber;
    }

    public void setStatusNumber(int statusNumber) {
        this.statusNumber = statusNumber;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }
}
