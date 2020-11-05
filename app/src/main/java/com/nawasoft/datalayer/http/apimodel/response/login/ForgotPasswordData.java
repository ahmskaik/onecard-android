package com.nawasoft.datalayer.http.apimodel.response.login;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordData {
    @SerializedName("data")
    private String message;

    public ForgotPasswordData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
