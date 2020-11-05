package com.nawasoft.companyapp.reports.mvp;

import android.util.Log;

import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.CompanyOrder;
import com.nawasoft.oneapp.R;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompanyOrdersPresenter implements CompanyOrdersMVP.Presenter {
    private CompanyOrdersMVP.Model model;
    private CompanyOrdersMVP.View view;

    private Subscription getCompanyOrdersSubscriptions;
    private Subscription getAllTotalsSubscriptions;

    private static final String TAG = "CompanyOrdersPresenter";

    public CompanyOrdersPresenter(CompanyOrdersMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getCompanyOrders() {
        view.setLoading();
        getCompanyOrdersSubscriptions = model.getCompanyOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CompanyOrder>>() {
                    @Override
                    public void onCompleted() {
                        getAllTotals();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "orders onError: ", e);
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(List<CompanyOrder> companyOrders) {
                        view.setOrders(companyOrders);
                    }
                });
    }


    @Override
    public void getAllTotals() {
        getAllTotalsSubscriptions = model.getAllTotals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        view.setTotals(strings);
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (CompanyOrdersMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getCompanyOrdersSubscriptions != null && !getCompanyOrdersSubscriptions.isUnsubscribed())
            getCompanyOrdersSubscriptions.unsubscribe();
        if (getAllTotalsSubscriptions != null && !getAllTotalsSubscriptions.isUnsubscribed())
            getAllTotalsSubscriptions.unsubscribe();
    }
}
