package com.nawasoft.oneapp.favouritefragment.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Offer;

import java.util.List;

import rx.Observable;

public interface FavouriteMVP {
    interface View extends IBaseView {
        void setOffers(List<Offer> offers);
        String getSearchQuery();
        List<Offer> getAllOffers();
        void setFilteredOffers(List<Offer> offers);
    }

    interface Presenter extends IBasePresenter {
        void getFavouriteOffers();
        void filterOffers();
    }

    interface Model {
        Observable<Offer> getFavouriteOffers();
    }
}
