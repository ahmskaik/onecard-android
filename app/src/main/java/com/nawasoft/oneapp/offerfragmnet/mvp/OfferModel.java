package com.nawasoft.oneapp.offerfragmnet.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.AddOfferToFavouritesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetIsOfferFavouriteRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetOfferDetailsRequest;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOfferDetailsData;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOfferDetailsResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.OfferSliderData;
import com.nawasoft.datalayer.http.apimodel.response.offers.favouriteoffers.GetIsOfferFavouriteResponse;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func1;

public class OfferModel implements OfferMVP.Model {

    private IApiService service;
    private ISharedPref sharedPref;

    public OfferModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<GetOfferDetailsData> getOfferDetails(String id) {
        Observable<GetOfferDetailsResponse> observable = service.getOfferDetails(
                new GetOfferDetailsRequest(ApiTags.GetOfferDetails.getValue(),
                        sharedPref.getLanguage(), id)
        );

        return observable.flatMap((Func1<GetOfferDetailsResponse, Observable<GetOfferDetailsData>>) getOfferDetailsResponse -> {
            GetOfferDetailsData data = getOfferDetailsResponse.getData().getData();
            if (data == null) {
                System.out.println("empty data");
                return Observable.empty();
            }
            Offer offer = data.getOffers().get(0);

            data.setRateAverage(
                    String.valueOf(
                            Math.round(Double.valueOf(data.getRateAverage()))
                    )
            );

            // base links
            String offerLink = data.getBaseOfferPhotoLink();
            String categoryLink = data.getBaseCategoryPhotoLink();
            String companyLink = data.getBaseCompanyPhotoLink();
            String sliderLink = data.getBaseSliderPhotoLink();
            if (data.getOfferSliderData() != null)
                for (OfferSliderData sliderData : data.getOfferSliderData()) {
                    sliderData.setPhoto(sliderLink + sliderData.getPhoto());
                }
            else
                data.setOfferSliderData(new ArrayList<>());

            offer.setOfferPhotoLink(offerLink + offer.getOfferPhotoLink());
            offer.setCategoryPhotoLink(categoryLink + offer.getCategoryPhotoLink());
            offer.setCompanyPhotoLink(companyLink + offer.getCompanyPhotoLink());
            if (offer.getDiscount() == null || offer.getDiscount().equalsIgnoreCase("null"))
                offer.setDiscount("0");
            return Observable.just(data);
        });
    }

    @Override
    public Observable<Boolean> getIsOfferFavourite(String offerId) {
        if (sharedPref.getMemberId() == -1)
            return Observable.empty();
        return service.getIsOfferFavourite(
                new GetIsOfferFavouriteRequest(
                        ApiTags.GetIsOfferFavourite.getValue(),
                        sharedPref.getLanguage(),
                        offerId,
                        String.valueOf(sharedPref.getMemberId())
                )
        ).flatMap((Func1<GetIsOfferFavouriteResponse, Observable<Boolean>>) getIsOfferFavouriteResponse -> {
            return Observable.just(getIsOfferFavouriteResponse.getData().getData().isFavourite() == 1);
        });
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPref.isLoggedIn();
    }

    @Override
    public Observable<Boolean> switchOfferFavourite(String offerId) {
        return service.addOfferToFavourite(
                new AddOfferToFavouritesRequest(
                        ApiTags.AddOfferToFavourites.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId()),
                        offerId
                )
        ).flatMap((Func1<BaseResponse, Observable<Boolean>>) baseResponse -> {
            boolean result = baseResponse.getMeta().getResponse().toLowerCase().contains("added");
            return Observable.just(result);
        });
    }
}
