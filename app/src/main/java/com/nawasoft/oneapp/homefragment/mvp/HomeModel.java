package com.nawasoft.oneapp.homefragment.mvp;

import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.HomeSlider;
import com.nawasoft.datalayer.model.Offer;


import rx.Observable;

public class HomeModel implements HomeMVP.Model {
    private HomeMVP.Repository repository;

    public HomeModel(HomeMVP.Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<HomeSlider> getHomeSlider() {
        return repository.getHomeSlider();
    }

    @Override
    public Observable<Category> getCategories() {
        return repository.getCategories();
    }

    @Override
    public Observable<Offer> getFeaturedOffers() {
        return repository.getFeaturedOffers();
    }

    @Override
    public Observable<Company> getFeaturedCompany() {
        return repository.getFeaturedCompany();
    }

    @Override
    public void refresh() {
        repository.refresh();
    }
}
