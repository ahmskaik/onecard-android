package com.nawasoft.datalayer.http.apimodel.response.activecard;

import com.google.gson.annotations.SerializedName;

public class ActiveCardData {
    @SerializedName("data")
    private String message;

    public ActiveCardData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
