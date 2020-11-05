package com.nawasoft.oneapp.offers.filter.mvp;

import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.Country;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FilterPresenter implements FilterMVP.Presenter {

    private FilterMVP.Model model;
    private FilterMVP.View view;

    private Subscription getCountriesSubscriptions;
    private Subscription getCitiesSubscriptions;

    public FilterPresenter(FilterMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getCountries() {
        getCountriesSubscriptions = model.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Country>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Country> countries) {
                        view.setCountries(countries);
                    }
                });
    }

    @Override
    public void getCities() {
        view.setCitiesLoading();
        getCitiesSubscriptions = model.getCities(view.getCountryId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<City>>() {
                    @Override
                    public void onCompleted() {
                        view.setCitiesLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setCitiesLoaded();
                    }

                    @Override
                    public void onNext(List<City> cities) {
                        view.setCities(cities);
                    }
                });
    }

    @Override
    public String getSavedCountry() {
        return model.getSavedCountry();
    }

    @Override
    public void saveCity(String cityId) {
        model.saveCity(cityId);
    }

    @Override
    public void saveCountry(String countryId) {
        model.saveCountry(countryId);
    }

    @Override
    public String getSavedCity() {
        return model.getSavedCity();
    }

    @Override
    public void setView(FilterMVP.View view) {
        this.view = view;
    }

    @Override
    public void cancelCalls() {
        if (getCountriesSubscriptions != null && !getCountriesSubscriptions.isUnsubscribed())
            getCountriesSubscriptions.unsubscribe();
        if (getCitiesSubscriptions != null && !getCitiesSubscriptions.isUnsubscribed())
            getCitiesSubscriptions.unsubscribe();
    }
}
