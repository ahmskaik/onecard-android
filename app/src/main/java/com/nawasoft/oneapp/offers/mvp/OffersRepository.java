package com.nawasoft.oneapp.offers.mvp;

import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.datalayer.cache.ICacheRepository;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetCategoriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetOffersRequest;
import com.nawasoft.datalayer.http.apimodel.response.categories.GetCategoriesResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOffersData;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOffersResponse;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;
import rx.functions.Func1;

public class OffersRepository implements OffersMVP.Repository {
    private IApiService service;
    private ISharedPref sharedPref;
    private ICacheRepository cacheRepository;


    public OffersRepository(IApiService service, ISharedPref sharedPref, ICacheRepository cacheRepository) {
        this.service = service;
        this.sharedPref = sharedPref;
        this.cacheRepository = cacheRepository;
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
            String basePhotoLink = getCategoriesResponse.getData().getData().getBasePhotoLink();
            for (Category category : getCategoriesResponse.getData().getData().getCategories()) {
                category.setPhotoLink(basePhotoLink + category.getPhotoLink());
            }
            cacheRepository.addCategories(getCategoriesResponse.getData().getData().getCategories());
            return Observable.from(getCategoriesResponse.getData().getData().getCategories());
        });
    }

    @Override
    public Observable<Category> getCategoriesFromMemory() {
        if (cacheRepository.hasCategories())
            return Observable.from(cacheRepository.getCategories());
        else
            return Observable.empty();
    }

    @Override
    public Observable<Category> getCategories() {
        return getCategoriesFromMemory().switchIfEmpty(getCategoriesFromNetwork());
    }

    @Override
    public Observable<Offer> getOffers(String categoryId, String countryId, String cityId) {
        Observable<GetOffersResponse> observable = service.getOffers(
                new GetOffersRequest(
                        categoryId.isEmpty() ? ApiTags.GetOffers.getValue() : ApiTags.GetCategoryCards.getValue(),
                        sharedPref.getLanguage(),
                        categoryId,
                        countryId,
                        cityId
                )
        );

        return observable.flatMap((Func1<GetOffersResponse, Observable<Offer>>) getOffersResponse -> {
            GetOffersData data = getOffersResponse.getData().getData();
            if (data == null)
                return Observable.empty();

            String baseOfferPhotoLink = data.getBaseOfferLink();
            String baseCategoryPhotoLink = data.getBaseCategoryLink();
            String baseCompanyPhotoLink = data.getBaseCompanyLink();

            for (Offer offer: data.getOffers()) {
                offer.setOfferPhotoLink(baseOfferPhotoLink + offer.getOfferPhotoLink());
                offer.setCategoryPhotoLink(baseCategoryPhotoLink + offer.getCategoryPhotoLink());
                offer.setCompanyPhotoLink(baseCompanyPhotoLink + offer.getCompanyPhotoLink());
                if (offer.getDiscount() == null || offer.getDiscount().equalsIgnoreCase("null"))
                    offer.setDiscount("0");
            }

            return Observable.from(data.getOffers());
        });
    }

    @Override
    public String getSavedCountry() {
        return sharedPref.getSavedCountry();
    }

    @Override
    public String getSavedCity() {
        return sharedPref.getSavedCity();
    }
}

