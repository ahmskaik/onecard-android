package com.nawasoft.datalayer.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CompanyCard {
    @SerializedName("cardId")
    private String cardI;
    @SerializedName("companyPhoto")
    private String companyPhotoLink;
    @SerializedName("categoryPhoto")
    private String categoryPhotoLink;
    @SerializedName("cardPhoto")
    private String offerPhotoLink;
    @SerializedName("theDiscount")
    private String discount;
    @SerializedName("cardDesc")
    private String offerDescription;
    @SerializedName("theText")
    private String offerText;

    private boolean selected;

    public CompanyCard(String cardI, String companyPhotoLink, String categoryPhotoLink, String offerPhotoLink, String discount, String offerDescription, String offerText) {
        this.cardI = cardI;
        this.companyPhotoLink = companyPhotoLink;
        this.categoryPhotoLink = categoryPhotoLink;
        this.offerPhotoLink = offerPhotoLink;
        this.discount = discount;
        this.offerDescription = offerDescription;
        this.offerText = offerText;
        selected = false;
    }

    @NonNull
    @Override
    public CompanyCard clone() {
        return new CompanyCard(
                cardI,
                companyPhotoLink,
                categoryPhotoLink,
                offerPhotoLink,
                discount,
                offerDescription,
                offerText
        );
    }

    public String getCardI() {
        return cardI;
    }

    public void setCardI(String cardI) {
        this.cardI = cardI;
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

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getOfferText() {
        return offerText;
    }

    public void setOfferText(String offerText) {
        this.offerText = offerText;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}