package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class ForgotPasswordRequest extends BaseRequest {

    @SerializedName("emailAddress")
    private String emailAddress;

    public ForgotPasswordRequest(String tag, String language, String emailAddress) {
        super(tag, language);
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
