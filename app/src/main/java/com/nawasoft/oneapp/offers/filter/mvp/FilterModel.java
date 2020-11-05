package com.nawasoft.oneapp.offers.filter.mvp;

import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.Country;

import java.util.List;

import rx.Observable;

public class FilterModel implements FilterMVP.Model {

    private FilterMVP.Repository repository;

    public FilterModel(FilterMVP.Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Country>> getCountries() {
        return repository.getCountries();
    }

    @Override
    public Observable<List<City>> getCities(String countryId) {
        return repository.getCities(countryId);
    }

    @Override
    public String getSavedCountry() {
        return repository.getSavedCountry();
    }

    @Override
    public void saveCity(String cityId) {
        repository.saveCity(cityId);
    }

    @Override
    public void saveCountry(String countryId) {
        repository.saveCountry(countryId);
    }

    @Override
    public String getSavedCity() {
        return repository.getSavedCity();
    }
}
