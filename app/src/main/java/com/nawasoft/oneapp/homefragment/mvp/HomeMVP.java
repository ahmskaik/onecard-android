package com.nawasoft.oneapp.homefragment.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.HomeSlider;
import com.nawasoft.datalayer.model.Offer;


import java.util.List;

import rx.Observable;

public interface HomeMVP {
    interface View extends IBaseView {
        void addHomeSliders(List<HomeSlider> sliderList);
        void addCategories(List<Category> categories);
        void addFeaturedOffers(List<Offer> offers);
        void addFeaturedCompanies(List<Company> companies);
    }

    interface Presenter extends IBasePresenter {
        void getHomeSlider();
        void getCategories();
        void getFeaturedOffers();
        void getFeaturedCompanies();
        void refresh();
    }

    interface Model {
        Observable<HomeSlider> getHomeSlider();
        Observable<Category> getCategories();
        Observable<Offer> getFeaturedOffers();
        Observable<Company> getFeaturedCompany();
        void refresh();
    }

    interface Repository {
        Observable<HomeSlider> getHomeSlider();
        Observable<HomeSlider> getHomeSliderFromMemory();
        Observable<HomeSlider> getHomeSliderFromNetwork();

        Observable<Category> getCategories();
        Observable<Category> getCategoriesFromMemory();
        Observable<Category> getCategoriesFromNetwork();

        Observable<Offer> getFeaturedOffers();
        Observable<Offer> getFeaturedOffersFromMemory();
        Observable<Offer> getFeaturedOffersFromNetwork();

        Observable<Company> getFeaturedCompany();
        Observable<Company> getFeaturedCompanyFromMemory();
        Observable<Company> getFeaturedCompanyFromNetwork();

        void refresh();
    }
}
