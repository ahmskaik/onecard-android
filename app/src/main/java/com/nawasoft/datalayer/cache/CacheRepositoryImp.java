package com.nawasoft.datalayer.cache;

import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.datalayer.model.HomeSlider;
import com.nawasoft.datalayer.model.Offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Singleton;

@Singleton
public class CacheRepositoryImp implements ICacheRepository {

    private List<HomeSlider> homeSliderList;
    private List<Category> categories;
    private Map<String, List<Offer>> featuredOffers; // (country&city, list of offers)
    private Map<String, List<Company>> featuredCompanies; // (country&city, list of companies)
    private List<Country> countries;

    @Override
    public void addHomeSlider(HomeSlider homeSlider) {
        if (homeSliderList == null)
            homeSliderList = new ArrayList<>();
        homeSliderList.add(homeSlider);
    }

    @Override
    public List<HomeSlider> getHomeSliderList() {
        return homeSliderList;
    }

    @Override
    public void clearHomeSliderList() {
        if (homeSliderList != null)
            homeSliderList.clear();
    }

    @Override
    public boolean hasHomeSliderList() {
        return homeSliderList != null && homeSliderList.size() > 0;
    }


    @Override
    public void addCategories(List<Category> categories) {
        if (this.categories == null)
            this.categories = new ArrayList<>();
        this.categories.clear();
        this.categories.addAll(categories);
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public void clearCategories() {
        if (categories != null)
            categories.clear();
    }

    @Override
    public boolean hasCategories() {
        return categories != null && categories.size() > 0;
    }

    @Override
    public void addFeaturedOffer(Offer offer, String key) {
        if (featuredOffers == null)
            featuredOffers = new TreeMap<>();
        List<Offer> offers = featuredOffers.get(key);
        if (offers == null) {
            offers = new ArrayList<>();
            featuredOffers.put(key, offers);
        }
        featuredOffers.get(key).add(offer);
    }

    @Override
    public List<Offer> getFeaturedOffers(String key) {
        return featuredOffers.get(key);
    }

    @Override
    public void clearFeaturedOffers() {
        if (featuredOffers != null)
            featuredOffers.clear();
    }

    @Override
    public boolean hasFeaturedOffers(String key) {
        return featuredOffers != null && featuredOffers.get(key) != null && featuredOffers.get(key).size() > 0;
    }

    @Override
    public void addFeaturedCompany(Company company, String key) {
        if (featuredCompanies == null)
            featuredCompanies = new TreeMap<>();
        List<Company> companies = featuredCompanies.get(key);
        if (companies == null) {
            companies = new ArrayList<>();
            featuredCompanies.put(key, companies);
        }
        featuredCompanies.get(key).add(company);
    }

    @Override
    public List<Company> getFeaturedCompanies(String key) {
        return featuredCompanies.get(key);
    }

    @Override
    public void clearFeaturedCompanies() {
        if (featuredCompanies != null)
            featuredCompanies.clear();
    }

    @Override
    public boolean hasFeaturedCompanies(String key) {
        return featuredCompanies != null && featuredCompanies.get(key) != null && featuredCompanies.get(key).size() > 0;
    }

    @Override
    public void addCountries(List<Country> countries) {
        if (this.countries == null)
            this.countries = new ArrayList<>();

        this.countries.clear();
        this.countries.addAll(countries);
    }

    @Override
    public List<Country> getCountries() {
        return countries;
    }

    @Override
    public void clearCountries() {
        countries = null;
    }

    @Override
    public void clearAllData() {
        clearCategories();
        clearCountries();
        clearFeaturedCompanies();
        clearFeaturedOffers();
        clearHomeSliderList();
    }
}
