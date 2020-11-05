package com.nawasoft.oneapp.offerfragmnet.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOfferDetailsData;
import com.nawasoft.datalayer.model.Branch;
import com.nawasoft.datalayer.model.Offer;

import java.util.List;

import rx.Observable;

public interface OfferMVP {
    interface View extends IBaseView {
        String getOfferId();
        void setOffer(Offer offer);
        void setRateAverage(String rateAverage);
        void setSliderPhotos(List<String> photos);
        void setIsOfferFavourite(boolean isOfferFavourite);
        void setBranches(List<Branch> branches);
    }

    interface Presenter extends IBasePresenter {
        void getOfferDetails();
        void getIsOfferFavourite();
        void switchOfferFavourite();
    }

    interface Model {
        boolean isLoggedIn();
        Observable<GetOfferDetailsData> getOfferDetails(String id);
        Observable<Boolean> getIsOfferFavourite(String offerId);
        Observable<Boolean> switchOfferFavourite(String offerId);
    }
}
