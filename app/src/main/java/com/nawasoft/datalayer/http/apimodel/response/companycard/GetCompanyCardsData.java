package com.nawasoft.datalayer.http.apimodel.response.companycard;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.CompanyCard;

import java.util.List;

public class GetCompanyCardsData {
    @SerializedName("photoLink")
    private String baseOfferPhotoLink;
    @SerializedName("categoryPhotoLink")
    private String baseCategoryPhotoLink;
    @SerializedName("companyPhotoLink")
    private String baseCompanyPhotoLink;
    @SerializedName("data")
    private List<CompanyCard> companyCards;

    public GetCompanyCardsData(String baseOfferPhotoLink, String baseCategoryPhotoLink, String baseCompanyPhotoLink, List<CompanyCard> companyCards) {
        this.baseOfferPhotoLink = baseOfferPhotoLink;
        this.baseCategoryPhotoLink = baseCategoryPhotoLink;
        this.baseCompanyPhotoLink = baseCompanyPhotoLink;
        this.companyCards = companyCards;
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

    public List<CompanyCard> getCompanyCards() {
        return companyCards;
    }

    public void setCompanyCards(List<CompanyCard> companyCards) {
        this.companyCards = companyCards;
    }
}