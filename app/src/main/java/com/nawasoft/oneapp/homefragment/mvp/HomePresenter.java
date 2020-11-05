package com.nawasoft.oneapp.homefragment.mvp;

import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.HomeSlider;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.oneapp.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter implements HomeMVP.Presenter {

    private HomeMVP.View view;
    private HomeMVP.Model model;

    private Subscription getHomeSliderSubscription;
    private Subscription getCategoriesSubscription;
    private Subscription getFeaturedOfferSubscription;
    private Subscription getFeaturedCompaniesSubscription;

    public HomePresenter(HomeMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getHomeSlider() {
        if (getHomeSliderSubscription != null)
            getHomeSliderSubscription.unsubscribe();

        view.setLoading();
        List<HomeSlider> sliderList = new ArrayList<>();
        getHomeSliderSubscription = model.getHomeSlider()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeSlider>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                        view.addHomeSliders(sliderList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(HomeSlider slider) {
                        sliderList.add(slider);
                    }
                });

    }

    @Override
    public void getCategories() {
        if (getCategoriesSubscription != null)
            getCategoriesSubscription.unsubscribe();

        view.setLoading();
        List<Category> categories = new ArrayList<>();
        getCategoriesSubscription = model.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Category>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                        view.addCategories(categories);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(Category category) {
                        categories.add(category);
                    }
                });

    }

    @Override
    public void getFeaturedOffers() {
        if (getFeaturedOfferSubscription != null)
            getFeaturedOfferSubscription.unsubscribe();

        view.setLoading();
        List<Offer> offers = new ArrayList<>();
        getFeaturedOfferSubscription = model.getFeaturedOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Offer>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                        view.addFeaturedOffers(offers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(Offer offer) {
                        offers.add(offer);
                    }
                });
    }

    @Override
    public void getFeaturedCompanies() {
        if (getFeaturedCompaniesSubscription != null)
            getFeaturedCompaniesSubscription.unsubscribe();

        view.setLoading();

        List<Company> companies = new ArrayList<>();
        getFeaturedCompaniesSubscription = model.getFeaturedCompany()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Company>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                        view.addFeaturedCompanies(companies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(Company company) {
                        companies.add(company);
                    }
                });
    }

    @Override
    public void refresh() {
        model.refresh();
        getHomeSlider();
        getCategories();
        getFeaturedOffers();
        getFeaturedCompanies();
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (HomeMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getHomeSliderSubscription != null && !getHomeSliderSubscription.isUnsubscribed()) {
            getHomeSliderSubscription.unsubscribe();
        }

        if (getCategoriesSubscription != null && !getCategoriesSubscription.isUnsubscribed()) {
            getCategoriesSubscription.unsubscribe();
        }

        if (getFeaturedOfferSubscription != null && !getFeaturedOfferSubscription.isUnsubscribed()) {
            getFeaturedOfferSubscription.unsubscribe();
        }

        if (getFeaturedCompaniesSubscription != null && !getFeaturedCompaniesSubscription.isUnsubscribed()) {
            getFeaturedCompaniesSubscription.unsubscribe();
        }
    }
}
