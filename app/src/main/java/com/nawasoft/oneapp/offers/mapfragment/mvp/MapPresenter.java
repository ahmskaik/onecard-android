package com.nawasoft.oneapp.offers.mapfragment.mvp;

import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.OfferMarker;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MapPresenter implements MapMVP.Presenter {

    private MapMVP.Model model;
    private MapMVP.View view;

    private Subscription getOffersSubscriptions;

    public MapPresenter(MapMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getOffers() {

        getOffersSubscriptions = model.getOffersMarkers(
                view.getLatitude(),
                view.getLongitude(),
                view.getCategoryId(),
                view.getCountryId(),
                view.getCityId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OfferMarker>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(OfferMarker offerMarker) {
                        view.addOfferMarker(offerMarker);
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (MapMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getOffersSubscriptions != null && !getOffersSubscriptions.isUnsubscribed())
            getOffersSubscriptions.unsubscribe();
    }
}
