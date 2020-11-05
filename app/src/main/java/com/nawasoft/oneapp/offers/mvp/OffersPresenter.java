package com.nawasoft.oneapp.offers.mvp;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Offer;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OffersPresenter implements OffersMVP.Presenter {
    private OffersMVP.Model model;
    private OffersMVP.View view;

    private Subscription getCategoriesSubscription;
    private Subscription getOffersSubscription;

    public OffersPresenter(OffersMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getCategories() {
        getCategoriesSubscription = model.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Category>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Category category) {
                        view.addCategory(category);
                    }
                });
    }


    @Override
    public void getOffers() {
        if (getOffersSubscription != null)
            getOffersSubscription.unsubscribe();

        List<Offer> offers = new ArrayList<>();

        view.setLoading();

        getOffersSubscription = model.getOffers(view.getCategoryId(), view.getCountryId(), view.getCityId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Offer>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                        view.addOffers(offers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.addOffers(offers);
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(Offer offer) {
                        offers.add(offer);
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (OffersMVP.View) view;
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
    public void getSavedFilter() {
        view.setSavedFilter(model.getSavedCountry(), model.getSavedCity());
    }

    @Override
    public void cancelCalls() {
        if (getCategoriesSubscription != null && !getCategoriesSubscription.isUnsubscribed())
            getCategoriesSubscription.unsubscribe();

        if (getOffersSubscription != null && !getOffersSubscription.isUnsubscribed()) {
            getOffersSubscription.unsubscribe();
        }
    }
}
