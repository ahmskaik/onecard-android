package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {
    @SerializedName("orderId")
    private String orderId;
    @SerializedName("cardId")
    private String offerId;
    @SerializedName("saleValue")
    private String saleValue;
    @SerializedName("rate")
    private String rate;
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("companyPhoto")
    private String companyPhotoLink;
    @SerializedName("theDiscount")
    private String discount;
    @SerializedName("currencyName")
    private String currencyName;
    @SerializedName("currencySymbol")
    private String currencySymbol;
    @SerializedName("date")
    private String date;

    public Order(String orderId, String offerId, String saleValue,String rate, String companyName, String companyPhotoLink, String discount, String currencyName, String currencySymbol, String date) {
        this.orderId = orderId;
        this.offerId = offerId;
        this.saleValue = saleValue;
        this.rate = rate;
        this.companyName = companyName;
        this.companyPhotoLink = companyPhotoLink;
        this.discount = discount;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(String saleValue) {
        this.saleValue = saleValue;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}