package com.nawasoft.oneapp.offers.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Offer;

import java.util.List;

import rx.Observable;

public interface OffersMVP {
    interface View extends IBaseView {
        void addCategory(Category category);
        String getCategoryId();
        void addOffers(List<Offer> offers);
        String getSearchQuery();
        List<Offer> getAllOffers();
        void setFilteredOffers(List<Offer> offers);
        String getCountryId();
        String getCityId();
        void setSavedFilter(String countryId, String cityId);
    }

    interface Presenter extends IBasePresenter {
        void getCategories();
        void getOffers();
        void filterOffers();
        void getSavedFilter();
    }

    interface Model {
        Observable<Category> getCategories();
        Observable<Offer> getOffers(String categoryId, String countryId, String cityId);
        String getSavedCountry();
        String getSavedCity();
    }

    interface Repository {
        Observable<Category> getCategoriesFromNetwork();
        Observable<Category> getCategoriesFromMemory();
        Observable<Category> getCategories();
        Observable<Offer> getOffers(String categoryId, String countryId, String cityId);

        String getSavedCountry();
        String getSavedCity();
    }
}
