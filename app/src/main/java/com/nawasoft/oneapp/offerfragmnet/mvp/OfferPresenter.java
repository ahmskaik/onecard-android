package com.nawasoft.oneapp.offerfragmnet.mvp;

import android.util.Log;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOfferDetailsData;
import com.nawasoft.datalayer.http.apimodel.response.offers.OfferSliderData;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OfferPresenter implements OfferMVP.Presenter {
    private OfferMVP.Model model;
    private OfferMVP.View view;

    private Subscription getOfferDetailsSubscriptions;
    private Subscription getIsOfferFavouriteSubscriptions;
    private Subscription switchOfferFavouriteSubscriptions;

    public OfferPresenter(OfferMVP.Model model) {
        this.model = model;
    }


    @Override
    public void getOfferDetails() {
        view.setLoading();
        getIsOfferFavourite();
        getOfferDetailsSubscriptions = model.getOfferDetails(view.getOfferId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetOfferDetailsData>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println(e.getMessage());
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(GetOfferDetailsData getOfferDetailsData) {
                        List<String> photos = new ArrayList<>();
                        for (OfferSliderData sliderData: getOfferDetailsData.getOfferSliderData())
                            photos.add(sliderData.getPhoto());
                        view.setOffer(getOfferDetailsData.getOffers().get(0));
                        view.setRateAverage(getOfferDetailsData.getRateAverage());
                        view.setSliderPhotos(photos);
                        view.setBranches(getOfferDetailsData.getBranches());
                    }
                });
    }

    @Override
    public void getIsOfferFavourite() {
        getIsOfferFavouriteSubscriptions = model.getIsOfferFavourite(view.getOfferId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Boolean isFavourite) {
                        view.setIsOfferFavourite(isFavourite);
                    }
                });
    }

    @Override
    public void switchOfferFavourite() {
        if (!model.isLoggedIn()) {
            view.showAlert(view.getString(R.string.must_login_message));
            return;
        }
        switchOfferFavouriteSubscriptions = model.switchOfferFavourite(view.getOfferId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean isFavourite) {
                        view.setIsOfferFavourite(isFavourite);
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (OfferMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getOfferDetailsSubscriptions != null && !getOfferDetailsSubscriptions.isUnsubscribed()) {
            getOfferDetailsSubscriptions.unsubscribe();
        }

        if (getIsOfferFavouriteSubscriptions != null && !getIsOfferFavouriteSubscriptions.isUnsubscribed()) {
            getIsOfferFavouriteSubscriptions.unsubscribe();
        }

        if (switchOfferFavouriteSubscriptions != null && !switchOfferFavouriteSubscriptions.isUnsubscribed()) {
            switchOfferFavouriteSubscriptions.unsubscribe();
        }
    }
}
