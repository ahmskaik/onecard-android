package com.nawasoft.oneapp.favouritefragment.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetFavouriteOffersRequest;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOffersData;
import com.nawasoft.datalayer.http.apimodel.response.offers.favouriteoffers.GetFavouriteOffersResponse;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;
import rx.functions.Func1;

public class FavouriteModel implements FavouriteMVP.Model {
    private IApiService service;
    private ISharedPref sharedPref;

    public FavouriteModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<Offer> getFavouriteOffers() {
        Observable<GetFavouriteOffersResponse> observable = service.getFavouriteOffers(
                new GetFavouriteOffersRequest(
                        ApiTags.GetFavouriteOffers.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId())
                )
        );

        return observable.flatMap((Func1<GetFavouriteOffersResponse, Observable<Offer>>) getOffersResponse -> {
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
}
