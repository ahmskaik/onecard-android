package com.nawasoft.oneapp.offers.mvp;

import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Offer;

import rx.Observable;

public class OffersModel implements OffersMVP.Model {
    private OffersMVP.Repository repository;

    public OffersModel(OffersMVP.Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Category> getCategories() {
        return repository.getCategories();
    }

    @Override
    public Observable<Offer> getOffers(String categoryId, String countryId, String cityId) {
        return repository.getOffers(categoryId, countryId, cityId);
    }

    @Override
    public String getSavedCountry() {
        return repository.getSavedCountry();
    }

    @Override
    public String getSavedCity() {
        return repository.getSavedCity();
    }
}
