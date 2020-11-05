package com.nawasoft.datalayer.http.apimodel.response.companydetails;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Branch;
import com.nawasoft.datalayer.model.Company;

import java.util.List;

public class GetCompanyDetailsData {
    @SerializedName("companyPhotoLink")
    private String basePhotoLink;

    @SerializedName("data")
    private List<Company> companies;

    @SerializedName("listOfBrunches")
    private List<Branch> branches;

    public GetCompanyDetailsData(String basePhotoLink, List<Company> companies, List<Branch> branches) {
        this.basePhotoLink = basePhotoLink;
        this.companies = companies;
        this.branches = branches;
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

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
