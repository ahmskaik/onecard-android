package com.nawasoft.oneapp.offers.mapfragment.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.datalayer.model.OfferMarker;

import rx.Observable;


public interface MapMVP {
    interface View {
        String getLatitude();

        String getLongitude();

        String getCategoryId();

        String getCountryId();

        String getCityId();

        void addOfferMarker(OfferMarker offerMarker);
    }

    interface Presenter extends IBasePresenter {
        void getOffers();
    }

    interface Model {
        Observable<OfferMarker> getOffersMarkers(
                String latitude,
                String longitude,
                String categoryId,
                String countryId,
                String cityId
        );
    }
}
