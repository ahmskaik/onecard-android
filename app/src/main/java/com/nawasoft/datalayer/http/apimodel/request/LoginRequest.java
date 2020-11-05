package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class LoginRequest extends BaseRequest {
    @SerializedName("emailAddress")
    private String emailAddress;
    @SerializedName("password")
    private String password;
    @SerializedName("tokenId")
    private String token;

    public LoginRequest(String tag, String language, String emailAddress, String password, String token) {
        super(tag, language);
        this.emailAddress = emailAddress;
        this.password = password;
        this.token = token;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
