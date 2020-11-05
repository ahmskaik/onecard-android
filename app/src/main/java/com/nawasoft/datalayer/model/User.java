package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("meId")
    private String userId;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("emailAddress")
    private String emailAddress;
    @SerializedName("thePassword")
    private String password;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("countryId")
    private String countryId;
    @SerializedName("gender")
    private String gender;
    @SerializedName("theAddress")
    private String address;
    @SerializedName("isActive")
    private String isActive;
    @SerializedName("isDeleted")
    private String isDeleted;
    @SerializedName("birthdate")
    private String birthday;
    @SerializedName("data")
    private String message;

    public User(String userId, String firstName, String lastName, String emailAddress, String password, String phoneNumber, String countryId, String gender, String address, String isActive, String isDeleted, String birthday, String message) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.gender = gender;
        this.address = address;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.birthday = birthday;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName == null ? "" : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress == null ? "" : emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryId() {
        return countryId == null ? "" : countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getGender() {
        return gender == null ? "" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getFullName() {
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }
}