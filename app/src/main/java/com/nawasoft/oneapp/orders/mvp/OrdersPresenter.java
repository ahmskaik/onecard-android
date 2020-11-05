package com.nawasoft.oneapp.orders.mvp;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Order;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrdersPresenter implements OrdersMVP.Presenter {

    private OrdersMVP.Model model;
    private OrdersMVP.View view;

    private Subscription getOrdersSubscriptions;
    private Subscription getAllTotalsSubscriptions;

    public OrdersPresenter(OrdersMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getOrders() {
        if (getOrdersSubscriptions != null)
            getOrdersSubscriptions.unsubscribe();

        view.setLoading();

        getOrdersSubscriptions = model.getOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Order>>() {
                    @Override
                    public void onCompleted() {
                        getTotals();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(List<Order> orders) {
                        view.setOrders(orders);
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (OrdersMVP.View) view;
    }

    @Override
    public void getTotals() {
        if (getAllTotalsSubscriptions != null)
            getAllTotalsSubscriptions.unsubscribe();

        getAllTotalsSubscriptions = model.getTotals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(List<String> totals) {
                        view.setTotals(totals);
                    }
                });
    }

    @Override
    public void cancelCalls() {
        if (getOrdersSubscriptions != null && !getOrdersSubscriptions.isUnsubscribed())
            getOrdersSubscriptions.unsubscribe();
        if (getAllTotalsSubscriptions != null && !getAllTotalsSubscriptions.isUnsubscribed())
            getAllTotalsSubscriptions.unsubscribe();
    }
}
