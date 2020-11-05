package com.nawasoft.oneapp.favouritefragment.mvp;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Offer;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FavouritePresenter implements FavouriteMVP.Presenter {
    private FavouriteMVP.Model model;
    private FavouriteMVP.View view;

    public FavouritePresenter(FavouriteMVP.Model model) {
        this.model = model;
    }

    private Subscription getFavouriteOfferSubscription;


    @Override
    public void getFavouriteOffers() {
        if (getFavouriteOfferSubscription != null)
            getFavouriteOfferSubscription.unsubscribe();

        view.setLoading();
        List<Offer> offers = new ArrayList<>();
        getFavouriteOfferSubscription = model.getFavouriteOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Offer>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                        view.setOffers(offers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showMessage(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(Offer offer) {
                        offers.add(offer);
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (FavouriteMVP.View) view;
    }


    @Override
    public void filterOffers() {
        List<Offer> allOffers = view.getAllOffers();
        if (allOffers == null) return;
        List<Offer> filteredOffers = new ArrayList<>();
        String query = view.getSearchQuery();
        for (Offer offer: allOffers) {
            if (offer.getCompanyName().toLowerCase().contains(query.toLowerCase())) {
                filteredOffers.add(offer);
            }
        }
        view.setFilteredOffers(filteredOffers);
    }

    @Override
    public void cancelCalls() {
        if (getFavouriteOfferSubscription != null && !getFavouriteOfferSubscription.isUnsubscribed()) {
            getFavouriteOfferSubscription.unsubscribe();
        }
    }
}
