package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

public class CurrencyTotal {
    @SerializedName("name")
    private String currencyName;
    @SerializedName("value")
    private String totalValue;

    public CurrencyTotal(String currencyName, String totalValue) {
        this.currencyName = currencyName;
        this.totalValue = totalValue;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }
}