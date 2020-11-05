package com.nawasoft.datalayer.http.apimodel.response.companies;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Company;

import java.util.List;

public class GetCompaniesResponseData {
    @SerializedName("companyPhotoLink")
    private String basePhotoLink;
    @SerializedName("data")
    private List<Company> companies;

    public GetCompaniesResponseData(String basePhotoLink, List<Company> companies) {
        this.basePhotoLink = basePhotoLink;
        this.companies = companies;
    }

    public String getBasePhotoLink() {
        return basePhotoLink;
    }

    public void setBasePhotoLink(String basePhotoLink) {
        this.basePhotoLink = basePhotoLink;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
