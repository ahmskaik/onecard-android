package com.nawasoft.datalayer.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("cuId")
    private String currencyId;
    @SerializedName("currencyName")
    private String currencyName;
    @SerializedName("currencySymbol")
    private String currencySymbol;

    public Currency(String currencyId, String currencyName, String currencySymbol) {
        this.currencyId = currencyId;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    @NonNull
    @Override
    public String toString() {
        return currencyName + " / " + currencySymbol;
    }
}
