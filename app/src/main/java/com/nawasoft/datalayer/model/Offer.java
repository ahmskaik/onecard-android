package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

public class Offer {
    @SerializedName("caId")
    private String offerId;
    @SerializedName("theDesc")
    private String description;
    @SerializedName("thePhoto")
    private String offerPhotoLink;
    @SerializedName("theDetails")
    private String details;
    @SerializedName("theDiscount")
    private String discount;
    @SerializedName("views")
    private String views;
    @SerializedName("theColor")
    private String color;
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
    @SerializedName("companyAddress")
    private String companyAddress;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("instagram")
    private String instagram;
    @SerializedName("whatsapp")
    private String whatsApp;
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("companyPhoto")
    private String companyPhotoLink;
    @SerializedName("categoryPhoto")
    private String categoryPhotoLink;


    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfferPhotoLink() {
        return offerPhotoLink;
    }

    public void setOfferPhotoLink(String offerPhotoLink) {
        this.offerPhotoLink = offerPhotoLink;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhotoLink() {
        return companyPhotoLink;
    }

    public void setCompanyPhotoLink(String companyPhotoLink) {
        this.companyPhotoLink = companyPhotoLink;
    }

    public String getCategoryPhotoLink() {
        return categoryPhotoLink;
    }

    public void setCategoryPhotoLink(String categoryPhotoLink) {
        this.categoryPhotoLink = categoryPhotoLink;
    }
}
