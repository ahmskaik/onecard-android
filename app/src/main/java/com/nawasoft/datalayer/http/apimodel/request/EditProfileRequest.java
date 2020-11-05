package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class EditProfileRequest extends BaseRequest {

    @SerializedName("memberId")
    private String memberId;
    @SerializedName("name")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("emailAddress")
    private String emailAddress;
    @SerializedName("theAddress")
    private String address;
    @SerializedName("countryId")
    private String countryId;
    @SerializedName("phone")
    private String phone;
    @SerializedName("birthdate")
    private String birthday;
    @SerializedName("gender") // 1 male, 2 female
    private String gender;

    public EditProfileRequest(String tag, String language, String memberId, String firstName, String lastName, String emailAddress, String address, String countryId, String phone, String birthday, String gender) {
        super(tag, language);
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.countryId = countryId;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
