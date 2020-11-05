package com.nawasoft.datalayer.cache;

import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.datalayer.model.HomeSlider;
import com.nawasoft.datalayer.model.Offer;

import java.util.List;


public interface ICacheRepository {
    void addHomeSlider(HomeSlider homeSlider);

    List<HomeSlider> getHomeSliderList();

    void clearHomeSliderList();

    boolean hasHomeSliderList();


    void addCategories(List<Category> categories);

    List<Category> getCategories();

    void clearCategories();

    boolean hasCategories();


    void addFeaturedOffer(Offer offer, String key);

    List<Offer> getFeaturedOffers(String key);

    void clearFeaturedOffers();

    boolean hasFeaturedOffers(String key);

    void addFeaturedCompany(Company company, String key);

    List<Company> getFeaturedCompanies(String key);

    void clearFeaturedCompanies();

    boolean hasFeaturedCompanies(String key);

    void addCountries(List<Country> countries);

    List<Country> getCountries();

    void clearCountries();


    void clearAllData();
}
