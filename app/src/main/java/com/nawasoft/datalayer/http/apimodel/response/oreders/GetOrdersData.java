package com.nawasoft.datalayer.http.apimodel.response.oreders;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Order;

import java.util.List;

public class GetOrdersData {
    @SerializedName("companyPhotoLink")
    private String companyBasePhotoLink;

    @SerializedName("data")
    List<Order> orders;

    public GetOrdersData(String companyBasePhotoLink, List<Order> orders) {
        this.companyBasePhotoLink = companyBasePhotoLink;
        this.orders = orders;
    }

    public String getCompanyBasePhotoLink() {
        return companyBasePhotoLink;
    }

    public void setCompanyBasePhotoLink(String companyBasePhotoLink) {
        this.companyBasePhotoLink = companyBasePhotoLink;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
