package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OfferMarker implements Serializable {
    @SerializedName("coId")
    private String companyId;
    @SerializedName("enTitle")
    private String englishTitle;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("lon")
    private String longitude;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName("emailAddress")
    private String emailAddress;
    @SerializedName("caId")
    private String offerId;
    @SerializedName("theDesc")
    private String description;
    @SerializedName("cardPhoto")
    private String offerPhotoLink;
    @SerializedName("theDiscount")
    private String discount;
    @SerializedName("companyPhoto")
    private String companyPhotoLink;
    @SerializedName("distance_in_km")
    private String distance;

    public OfferMarker(String companyId, String englishTitle, String latitude, String longitude, String phoneNumber, String mobileNumber, String emailAddress, String offerId, String description, String offerPhotoLink, String discount, String companyPhotoLink, String distance) {
        this.companyId = companyId;
        this.englishTitle = englishTitle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.offerId = offerId;
        this.description = description;
        this.offerPhotoLink = offerPhotoLink;
        this.discount = discount;
        this.companyPhotoLink = companyPhotoLink;
        this.distance = distance;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCompanyPhotoLink() {
        return companyPhotoLink;
    }

    public void setCompanyPhotoLink(String companyPhotoLink) {
        this.companyPhotoLink = companyPhotoLink;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
