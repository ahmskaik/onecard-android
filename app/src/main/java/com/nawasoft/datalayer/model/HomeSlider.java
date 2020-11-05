package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

public class HomeSlider {
    @SerializedName("slId")
    private String sliderId;

    @SerializedName("thePhoto")
    private String photoLink;

    public HomeSlider(String sliderId, String photoLink) {
        this.sliderId = sliderId;
        this.photoLink = photoLink;
    }

    public String getSliderId() {
        return sliderId;
    }

    public void setSliderId(String sliderId) {
        this.sliderId = sliderId;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
