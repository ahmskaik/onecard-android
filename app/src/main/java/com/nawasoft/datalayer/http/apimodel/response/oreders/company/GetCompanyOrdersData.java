package com.nawasoft.datalayer.http.apimodel.response.oreders.company;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.CompanyOrder;

import java.util.List;

public class GetCompanyOrdersData {
    @SerializedName("companyPhotoLink")
    private String companyBasePhotoLink;
    @SerializedName("data")
    private List<CompanyOrder> companyOrders;

    public GetCompanyOrdersData(String companyBasePhotoLink, List<CompanyOrder> companyOrders) {
        this.companyBasePhotoLink = companyBasePhotoLink;
        this.companyOrders = companyOrders;
    }

    public String getCompanyBasePhotoLink() {
        return companyBasePhotoLink;
    }

    public void setCompanyBasePhotoLink(String companyBasePhotoLink) {
        this.companyBasePhotoLink = companyBasePhotoLink;
    }

    public List<CompanyOrder> getCompanyOrders() {
        return companyOrders;
    }

    public void setCompanyOrders(List<CompanyOrder> companyOrders) {
        this.companyOrders = companyOrders;
    }
}
