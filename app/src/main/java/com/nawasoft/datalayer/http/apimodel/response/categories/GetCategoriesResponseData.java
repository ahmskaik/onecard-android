package com.nawasoft.datalayer.http.apimodel.response.categories;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Category;

import java.util.List;

public class GetCategoriesResponseData {

    @SerializedName("data")
    private List<Category> categories;
    @SerializedName("categoryPhotoLink")
    private String basePhotoLink;


    public GetCategoriesResponseData(List<Category> categories, String basePhotoLink) {
        this.categories = categories;
        this.basePhotoLink = basePhotoLink;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getBasePhotoLink() {
        return basePhotoLink;
    }

    public void setBasePhotoLink(String basePhotoLink) {
        this.basePhotoLink = basePhotoLink;
    }
}
