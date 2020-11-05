package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

public class CompanyOrder {
    @SerializedName("date")
    private String date;
    @SerializedName("memberName")
    private String memberName;
    @SerializedName("companyPhoto")
    private String companyPhotoLink;
    @SerializedName("billValue")
    private String billValue;
    @SerializedName("saleValue")
    private String saleValue;
    @SerializedName("theDiscount")
    private String discount;
    @SerializedName("currencyName")
    private String currencyName;
    @SerializedName("currencySymbol")
    private String currencySymbol;

    public CompanyOrder(String date, String memberName, String companyPhotoLink, String billValue, String saleValue, String discount, String currencyName, String currencySymbol) {
        this.date = date;
        this.memberName = memberName;
        this.companyPhotoLink = companyPhotoLink;
        this.billValue = billValue;
        this.saleValue = saleValue;
        this.discount = discount;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCompanyPhotoLink() {
        return companyPhotoLink;
    }

    public void setCompanyPhotoLink(String companyPhotoLink) {
        this.companyPhotoLink = companyPhotoLink;
    }

    public String getBillValue() {
        return billValue;
    }

    public void setBillValue(String billValue) {
        this.billValue = billValue;
    }

    public String getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(String saleValue) {
        this.saleValue = saleValue;
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
}
