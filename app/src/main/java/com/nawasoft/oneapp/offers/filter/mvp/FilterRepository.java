package com.nawasoft.oneapp.offers.filter.mvp;

import com.nawasoft.datalayer.cache.ICacheRepository;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetCitiesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCountriesRequest;
import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.List;

import rx.Observable;

public class FilterRepository implements FilterMVP.Repository {

    private IApiService service;
    private ISharedPref sharedPref;
    private ICacheRepository cacheRepository;

    public FilterRepository(IApiService service, ISharedPref sharedPref, ICacheRepository cacheRepository) {
        this.service = service;
        this.sharedPref = sharedPref;
        this.cacheRepository = cacheRepository;
    }


    @Override
    public Observable<List<Country>> getCountries() {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());
    }

    @Override
    public Observable<List<Country>> getCountriesFromNetwork() {
        return service.getCountries(
                new GetCountriesRequest(ApiTags.GetCountries.getValue(), sharedPref.getLanguage())
        ).doOnNext(countries -> {
            cacheRepository.addCountries(countries);
        });
    }

    @Override
    public Observable<List<Country>> getCountriesFromMemory() {
        if (cacheRepository.getCountries() == null)
            return Observable.empty();
        return Observable.just(cacheRepository.getCountries());
    }

    @Override
    public Observable<List<City>> getCities(String countryId) {
        sharedPref.saveCountry(countryId);
        return service.getCities(
                new GetCitiesRequest(ApiTags.GetCities.getValue(), sharedPref.getLanguage(), countryId)
        );
    }

    @Override
    public String getSavedCountry() {
        return sharedPref.getSavedCountry();
    }

    @Override
    public void saveCity(String cityId) {
        sharedPref.saveCity(cityId);
        System.out.println("City " + cityId + " Saved");
    }

    @Override
    public void saveCountry(String countryId) {
        sharedPref.saveCountry(countryId);
        System.out.println("Country " + countryId + " Saved");
    }

    @Override
    public String getSavedCity() {
        return sharedPref.getSavedCity();
    }
}
