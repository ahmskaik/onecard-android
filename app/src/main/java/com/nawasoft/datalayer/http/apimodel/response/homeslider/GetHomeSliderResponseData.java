package com.nawasoft.datalayer.http.apimodel.response.homeslider;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.HomeSlider;

import java.util.List;

public class GetHomeSliderResponseData {
    @SerializedName("photoLink")
    private String basePhotoLink;

    @SerializedName("data")
    private List<HomeSlider> homeSliders;


    public GetHomeSliderResponseData(String basePhotoLink, List<HomeSlider> homeSliders) {
        this.basePhotoLink = basePhotoLink;
        this.homeSliders = homeSliders;
    }

    public String getBasePhotoLink() {
        return basePhotoLink;
    }

    public void setBasePhotoLink(String basePhotoLink) {
        this.basePhotoLink = basePhotoLink;
    }

    public List<HomeSlider> getHomeSliders() {
        return homeSliders;
    }

    public void setHomeSliders(List<HomeSlider> homeSliders) {
        this.homeSliders = homeSliders;
    }
}
