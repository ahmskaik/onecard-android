package com.nawasoft.datalayer.http.apimodel.response.offers.mapoffers;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.OfferMarker;

import java.util.List;

public class GetNearestOffersData {
    @SerializedName("photoLink")
    private String baseOfferPhotoLink;
    @SerializedName("companyPhotoLink")
    private String baseCompanyPhotoLink;
    @SerializedName("data")
    private List<OfferMarker> offerMarkers;

    public GetNearestOffersData(String baseOfferPhotoLink, String baseCompanyPhotoLink, List<OfferMarker> offerMarkers) {
        this.baseOfferPhotoLink = baseOfferPhotoLink;
        this.baseCompanyPhotoLink = baseCompanyPhotoLink;
        this.offerMarkers = offerMarkers;
    }

    public String getBaseOfferPhotoLink() {
        return baseOfferPhotoLink;
    }

    public void setBaseOfferPhotoLink(String baseOfferPhotoLink) {
        this.baseOfferPhotoLink = baseOfferPhotoLink;
    }

    public String getBaseCompanyPhotoLink() {
        return baseCompanyPhotoLink;
    }

    public void setBaseCompanyPhotoLink(String baseCompanyPhotoLink) {
        this.baseCompanyPhotoLink = baseCompanyPhotoLink;
    }

    public List<OfferMarker> getOfferMarkers() {
        return offerMarkers;
    }

    public void setOfferMarkers(List<OfferMarker> offerMarkers) {
        this.offerMarkers = offerMarkers;
    }
}
