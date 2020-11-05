package com.nawasoft.datalayer.http.apimodel.response.editprofile;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.User;

import java.util.List;

public class EditProfileData {
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("data")
    private List<User> users;

    public EditProfileData(String memberId, List<User> users) {
        this.memberId = memberId;
        this.users = users;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
