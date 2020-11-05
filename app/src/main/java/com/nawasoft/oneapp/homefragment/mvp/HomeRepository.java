package com.nawasoft.oneapp.homefragment.mvp;

import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.HomeSlider;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.datalayer.cache.ICacheRepository;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetCategoriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetFeaturedCompaniesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetFeaturedOffersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetHomeSliderRequest;
import com.nawasoft.datalayer.http.apimodel.response.categories.GetCategoriesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companies.GetFeaturedCompaniesResponse;
import com.nawasoft.datalayer.http.apimodel.response.homeslider.GetHomeSliderResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetFeaturedOffersResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOffersData;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;
import rx.functions.Func1;

public class HomeRepository implements HomeMVP.Repository {
    private IApiService service;
    private ISharedPref sharedPref;
    private ICacheRepository cacheRepository;

    public HomeRepository(IApiService service, ISharedPref sharedPref, ICacheRepository cacheRepository) {
        this.service = service;
        this.sharedPref = sharedPref;
        this.cacheRepository = cacheRepository;
    }

    private String getCountryCityKey() {
        return sharedPref.getSavedCountry() + "," + sharedPref.getSavedCity();
    }

    @Override
    public Observable<HomeSlider> getHomeSliderFromMemory() {
        if (cacheRepository.hasHomeSliderList()) {
            return Observable.from(cacheRepository.getHomeSliderList());
        } else {
            return Observable.empty();
        }
    }

    @Override
    public Observable<HomeSlider> getHomeSliderFromNetwork() {
        Observable<GetHomeSliderResponse> observable = service.getHomeSlider(
                new GetHomeSliderRequest(
                        ApiTags.GetHomeSlider.getValue(),
                        sharedPref.getLanguage()
                )
        );

        return observable
                .flatMap((Func1<GetHomeSliderResponse, Observable<HomeSlider>>) getHomeSliderResponse -> {
                    if (getHomeSliderResponse.getData().getData() == null)
                        return Observable.empty();
                    String basePhotoLink = getHomeSliderResponse.getData().getData().getBasePhotoLink();
                    for (HomeSlider homeSlider : getHomeSliderResponse.getData().getData().getHomeSliders()) {
                        homeSlider.setPhotoLink(basePhotoLink + homeSlider.getPhotoLink());
                    }
                    return Observable.from(getHomeSliderResponse.getData().getData().getHomeSliders());
                })
                .doOnNext((homeSlider -> cacheRepository.addHomeSlider(homeSlider)));
    }

    @Override
    public Observable<Category> getCategoriesFromMemory() {
        if (cacheRepository.hasCategories())
            return Observable.from(cacheRepository.getCategories());
        else
            return Observable.empty();
    }

    @Override
    public Observable<Category> getCategoriesFromNetwork() {
        Observable<GetCategoriesResponse> observable = service.getCategories(
                new GetCategoriesRequest(
                        ApiTags.GetCategories.getValue(),
                        sharedPref.getLanguage()
                )
        );

        return observable.flatMap((Func1<GetCategoriesResponse, Observable<Category>>) getCategoriesResponse -> {
            if (getCategoriesResponse.getData().getData() == null)
                return Observable.empty();
            String basePhotoLink = getCategoriesResponse.getData().getData().getBasePhotoLink();
            for (Category category : getCategoriesResponse.getData().getData().getCategories()) {
                category.setPhotoLink(basePhotoLink + category.getPhotoLink());
            }
            cacheRepository.addCategories(getCategoriesResponse.getData().getData().getCategories());
            return Observable.from(getCategoriesResponse.getData().getData().getCategories());
        });
    }

    @Override
    public Observable<Category> getCategories() {
        return getCategoriesFromMemory().switchIfEmpty(getCategoriesFromNetwork());
    }

    @Override
    public Observable<HomeSlider> getHomeSlider() {
        return getHomeSliderFromMemory().switchIfEmpty(getHomeSliderFromNetwork());
    }

    @Override
    public Observable<Offer> getFeaturedOffersFromNetwork() {
        Observable<GetFeaturedOffersResponse> observable = service.getFeaturedOffers(
                new GetFeaturedOffersRequest(
                        ApiTags.GetFeaturedOffers.getValue(),
                        sharedPref.getLanguage(),
                        sharedPref.getSavedCountry(),
                        sharedPref.getSavedCity()
                )
        );

        return observable
                .flatMap((Func1<GetFeaturedOffersResponse, Observable<Offer>>) getFeaturedOffersResponse -> {
                    GetOffersData data = getFeaturedOffersResponse.getData().getData();
                    if (data == null)
                        return Observable.empty();
                    String baseOfferLink = data.getBaseOfferLink(),
                            baseCategoryLink = data.getBaseCategoryLink(),
                            baseCompanyLink = data.getBaseCompanyLink();

                    for (Offer offer : data.getOffers()) {
                        offer.setOfferPhotoLink(baseOfferLink + offer.getOfferPhotoLink());
                        offer.setCategoryPhotoLink(baseCategoryLink + offer.getCategoryPhotoLink());
                        offer.setCompanyPhotoLink(baseCompanyLink + offer.getCompanyPhotoLink());
                        if (offer.getDiscount() == null || offer.getDiscount().equalsIgnoreCase("null"))
                            offer.setDiscount("0");
                    }

                    return Observable.from(data.getOffers());
                })
                .doOnNext(offer -> cacheRepository.addFeaturedOffer(offer, getCountryCityKey()));
    }


    @Override
    public Observable<Offer> getFeaturedOffersFromMemory() {
        if (cacheRepository.hasFeaturedOffers(getCountryCityKey()))
            return Observable.from(cacheRepository.getFeaturedOffers(getCountryCityKey()));

        return Observable.empty();
    }


    @Override
    public Observable<Offer> getFeaturedOffers() {
        return getFeaturedOffersFromMemory().switchIfEmpty(getFeaturedOffersFromNetwork());
    }

    @Override
    public Observable<Company> getFeaturedCompanyFromNetwork() {
        Observable<GetFeaturedCompaniesResponse> observable = service.getFeaturedCompanies(
                new GetFeaturedCompaniesRequest(
                        ApiTags.GetFeaturedCompanies.getValue(),
                        sharedPref.getLanguage(),
                        sharedPref.getSavedCountry(),
                        sharedPref.getSavedCity()
                )
        );

        return observable.flatMap((Func1<GetFeaturedCompaniesResponse, Observable<Company>>) getFeaturedCompaniesResponse -> {
            if (getFeaturedCompaniesResponse.getData().getData() == null)
                return Observable.empty();

            String basePhotoLink = getFeaturedCompaniesResponse.getData().getData().getBasePhotoLink();
            for (Company company : getFeaturedCompaniesResponse.getData().getData().getCompanies()) {
                company.setCompanyPhotoLink(basePhotoLink + company.getCompanyPhotoLink());
            }
            return Observable.from(getFeaturedCompaniesResponse.getData().getData().getCompanies());
        }).doOnNext(company -> cacheRepository.addFeaturedCompany(company, getCountryCityKey()));

    }

    @Override
    public Observable<Company> getFeaturedCompanyFromMemory() {
        if (cacheRepository.hasFeaturedCompanies(getCountryCityKey()))
            return Observable.from(cacheRepository.getFeaturedCompanies(getCountryCityKey()));
        return Observable.empty();
    }

    @Override
    public Observable<Company> getFeaturedCompany() {
        return getFeaturedCompanyFromMemory().switchIfEmpty(getFeaturedCompanyFromNetwork());
    }


    @Override
    public void refresh() {
        cacheRepository.clearCategories();
        cacheRepository.clearHomeSliderList();
        cacheRepository.clearFeaturedCompanies();
        cacheRepository.clearFeaturedOffers();
    }
}
