package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("coId")
    private String companyId;
    @SerializedName("theTitle")
    private String title;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName("emailAddress")
    private String emailAddress;
    @SerializedName("webSite")
    private String website;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("lon")
    private String longitude;
    @SerializedName("thePhoto")
    private String companyPhotoLink;
    @SerializedName("theAddress")
    private String address;
    @SerializedName("cityName")
    private String cityName;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("instagram")
    private String instagram;
    @SerializedName("whatsapp")
    private String whatsApp;


    // for map
    @SerializedName("distance_in_km")
    private String distance;
    @SerializedName("enTitle")
    private String englishTitle;

    public Company(String companyId, String title, String phoneNumber, String mobileNumber, String emailAddress, String website, String latitude, String longitude, String companyPhotoLink, String address, String facebook, String instagram, String whatsApp) {
        this.companyId = companyId;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
        this.companyPhotoLink = companyPhotoLink;
        this.address = address;
        this.facebook = facebook;
        this.instagram = instagram;
        this.whatsApp = whatsApp;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCompanyPhotoLink() {
        return companyPhotoLink;
    }

    public void setCompanyPhotoLink(String companyPhotoLink) {
        this.companyPhotoLink = companyPhotoLink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }
}
