package com.nawasoft.datalayer.http.apimodel.response.offers;

import com.google.gson.annotations.SerializedName;

public class OfferSliderData {
    @SerializedName("thePhoto")
    private String photo;

    public OfferSliderData(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

