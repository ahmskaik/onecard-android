package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class ResetPasswordRequest extends BaseRequest {
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("oldPassword")
    private String oldPassword;
    @SerializedName("password")
    private String newPassword;

    public ResetPasswordRequest(String tag, String language, String memberId, String oldPassword, String newPassword) {
        super(tag, language);
        this.memberId = memberId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
