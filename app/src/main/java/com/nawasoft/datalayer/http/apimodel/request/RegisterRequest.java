package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class RegisterRequest extends BaseRequest {

    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("emailAddress")
    private String emailAddress;
    @SerializedName("password")
    private String password;
    @SerializedName("countryId")
    private String countryId;
    @SerializedName("tokenId")
    private String token;

    public RegisterRequest(String tag, String language, String name, String phone, String emailAddress, String password, String countryId, String token) {
        super(tag, language);
        this.name = name;
        this.phone = phone;
        this.emailAddress = emailAddress;
        this.password = password;
        this.countryId = countryId;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
