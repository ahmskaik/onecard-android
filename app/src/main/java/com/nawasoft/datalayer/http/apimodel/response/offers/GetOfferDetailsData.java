package com.nawasoft.datalayer.http.apimodel.response.offers;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Branch;
import com.nawasoft.datalayer.model.Offer;

import java.util.List;

public class GetOfferDetailsData {
    @SerializedName("photoLink")
    private String baseOfferPhotoLink;
    @SerializedName("categoryPhotoLink")
    private String baseCategoryPhotoLink;
    @SerializedName("companyPhotoLink")
    private String baseCompanyPhotoLink;
    @SerializedName("sliderPhotoLink")
    private String baseSliderPhotoLink;
    @SerializedName("cardSlider")
    private List<OfferSliderData> offerSliderData;
    @SerializedName("RateAvg")
    private String rateAverage;
    @SerializedName("data")
    private List<Offer> offers;
    @SerializedName("listOfBrunches")
    private List<Branch> branches;

    public GetOfferDetailsData(String baseOfferPhotoLink, String baseCategoryPhotoLink, String baseCompanyPhotoLink, String baseSliderPhotoLink, List<OfferSliderData> offerSliderData, String rateAverage, List<Offer> offers, List<Branch> branches) {
        this.baseOfferPhotoLink = baseOfferPhotoLink;
        this.baseCategoryPhotoLink = baseCategoryPhotoLink;
        this.baseCompanyPhotoLink = baseCompanyPhotoLink;
        this.baseSliderPhotoLink = baseSliderPhotoLink;
        this.offerSliderData = offerSliderData;
        this.rateAverage = rateAverage;
        this.offers = offers;
        this.branches = branches;
    }

    public String getBaseOfferPhotoLink() {
        return baseOfferPhotoLink;
    }

    public void setBaseOfferPhotoLink(String baseOfferPhotoLink) {
        this.baseOfferPhotoLink = baseOfferPhotoLink;
    }

    public String getBaseCategoryPhotoLink() {
        return baseCategoryPhotoLink;
    }

    public void setBaseCategoryPhotoLink(String baseCategoryPhotoLink) {
        this.baseCategoryPhotoLink = baseCategoryPhotoLink;
    }

    public String getBaseCompanyPhotoLink() {
        return baseCompanyPhotoLink;
    }

    public void setBaseCompanyPhotoLink(String baseCompanyPhotoLink) {
        this.baseCompanyPhotoLink = baseCompanyPhotoLink;
    }

    public String getBaseSliderPhotoLink() {
        return baseSliderPhotoLink;
    }

    public void setBaseSliderPhotoLink(String baseSliderPhotoLink) {
        this.baseSliderPhotoLink = baseSliderPhotoLink;
    }

    public List<OfferSliderData> getOfferSliderData() {
        return offerSliderData;
    }

    public void setOfferSliderData(List<OfferSliderData> offerSliderData) {
        this.offerSliderData = offerSliderData;
    }

    public String getRateAverage() {
        return rateAverage;
    }

    public void setRateAverage(String rateAverage) {
        this.rateAverage = rateAverage;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
