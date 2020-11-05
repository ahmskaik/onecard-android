package com.nawasoft.datalayer.http.apimodel.response.offers.favouriteoffers;

import com.google.gson.annotations.SerializedName;

public class GetIsOfferFavouriteData {
    @SerializedName("data")
    private int isFavourite;

    public GetIsOfferFavouriteData(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public int isFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }
}
