package com.nawasoft.oneapp.offers.filter.mvp;

import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.Country;

import java.util.List;

import rx.Observable;

public interface FilterMVP {

    interface View {
        String getCountryId();
        void setCountries(List<Country> countries);
        void setCities(List<City> cities);
        void setCitiesLoading();
        void setCitiesLoaded();
    }

    interface Presenter {
        void setView(FilterMVP.View view);
        void getCountries();
        void getCities();
        void cancelCalls();

        String getSavedCountry();

        void saveCity(String cityId);
        void saveCountry(String countryId);
        String getSavedCity();
    }

    interface Model {
        Observable<List<Country>> getCountries();
        Observable<List<City>> getCities(String countryId);

        String getSavedCountry();

        void saveCity(String cityId);
        void saveCountry(String countryId);
        String getSavedCity();
    }

    interface Repository {
        Observable<List<Country>> getCountries();
        Observable<List<Country>> getCountriesFromNetwork();
        Observable<List<Country>> getCountriesFromMemory();

        Observable<List<City>> getCities(String countryId);

        String getSavedCountry();

        void saveCity(String cityId);
        void saveCountry(String countryId);
        String getSavedCity();
    }
}
