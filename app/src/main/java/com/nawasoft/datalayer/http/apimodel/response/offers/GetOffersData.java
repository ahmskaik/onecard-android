package com.nawasoft.datalayer.http.apimodel.response.offers;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Offer;

import java.util.List;

public class GetOffersData {
    @SerializedName("photoLink")
    private String baseOfferLink;
    @SerializedName("categoryPhotoLink")
    private String baseCategoryLink;
    @SerializedName("companyPhotoLink")
    private String baseCompanyLink;

    @SerializedName("data")
    private List<Offer> offers;

    public GetOffersData(String baseOfferLink, String baseCategoryLink, String baseCompanyLink, List<Offer> offers) {
        this.baseOfferLink = baseOfferLink;
        this.baseCategoryLink = baseCategoryLink;
        this.baseCompanyLink = baseCompanyLink;
        this.offers = offers;
    }

    public String getBaseOfferLink() {
        return baseOfferLink;
    }

    public void setBaseOfferLink(String baseOfferLink) {
        this.baseOfferLink = baseOfferLink;
    }

    public String getBaseCategoryLink() {
        return baseCategoryLink;
    }

    public void setBaseCategoryLink(String baseCategoryLink) {
        this.baseCategoryLink = baseCategoryLink;
    }

    public String getBaseCompanyLink() {
        return baseCompanyLink;
    }

    public void setBaseCompanyLink(String baseCompanyLink) {
        this.baseCompanyLink = baseCompanyLink;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
