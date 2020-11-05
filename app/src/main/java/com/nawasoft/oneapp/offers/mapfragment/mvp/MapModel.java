package com.nawasoft.oneapp.offers.mapfragment.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetNearestOffersRequest;
import com.nawasoft.datalayer.http.apimodel.response.offers.mapoffers.GetNearestOfferResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.mapoffers.GetNearestOffersData;
import com.nawasoft.datalayer.model.OfferMarker;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;
import rx.functions.Func1;

public class MapModel implements MapMVP.Model {
    private IApiService service;
    private ISharedPref sharedPref;

    public MapModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<OfferMarker> getOffersMarkers(
            String latitude,
            String longitude,
            String categoryId,
            String countryId,
            String cityId
    ) {

        if (!countryId.isEmpty() || !cityId.isEmpty()) {
            longitude = "";
            latitude = "";
        }

        Observable<GetNearestOfferResponse> observable = service.getNearestOffers(
                new GetNearestOffersRequest(
                        ApiTags.GetNearestCompanies.getValue(),
                        sharedPref.getLanguage(),
                        latitude,
                        longitude,
                        categoryId,
                        countryId,
                        cityId
                )
        );

        return observable.flatMap((Func1<GetNearestOfferResponse, Observable<OfferMarker>>) getNearestOfferResponse -> {
            GetNearestOffersData data = getNearestOfferResponse.getData().getData();
            if (data == null)
                return Observable.empty();
            String baseOfferLink = data.getBaseOfferPhotoLink();
            String baseCompanyLink = data.getBaseCompanyPhotoLink();
            for (OfferMarker offerMarker : data.getOfferMarkers()) {
                offerMarker.setCompanyPhotoLink(baseCompanyLink + offerMarker.getCompanyPhotoLink());
                offerMarker.setOfferPhotoLink(baseOfferLink + offerMarker.getOfferPhotoLink());
            }
            return Observable.from(data.getOfferMarkers());
        });
    }
}
