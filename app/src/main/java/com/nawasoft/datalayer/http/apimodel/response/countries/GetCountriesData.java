package com.nawasoft.datalayer.http.apimodel.response.countries;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Country;

import java.util.List;

public class GetCountriesData {

    @SerializedName("countryPhotoLink")
    private String countriesBasePhotoLink;

    @SerializedName("data")
    private List<Country> countries;

    public GetCountriesData(String countriesBasePhotoLink, List<Country> countries) {
        this.countriesBasePhotoLink = countriesBasePhotoLink;
        this.countries = countries;
    }

    public String getCountriesBasePhotoLink() {
        return countriesBasePhotoLink;
    }

    public void setCountriesBasePhotoLink(String countriesBasePhotoLink) {
        this.countriesBasePhotoLink = countriesBasePhotoLink;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
