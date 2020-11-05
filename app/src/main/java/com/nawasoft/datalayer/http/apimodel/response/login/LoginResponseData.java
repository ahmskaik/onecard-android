package com.nawasoft.datalayer.http.apimodel.response.login;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.User;

import java.util.List;

public class LoginResponseData {

    @SerializedName("member")
    private List<User> users;
    @SerializedName("company")
    private List<Company> companies;
    @SerializedName("loginType")
    private List<String> loginType;
    @SerializedName("data")
    private List<String> message;

    public LoginResponseData(List<User> users, List<Company> companies, List<String> loginType, List<String> message) {
        this.users = users;
        this.companies = companies;
        this.loginType = loginType;
        this.message = message;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getLoginType() {
        return loginType.get(0);
    }

    public void setLoginType(List<String> loginType) {
        this.loginType = loginType;
    }

    public String getMessage() {
        return message.get(0);
    }


}
