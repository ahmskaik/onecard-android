package com.nawasoft.oneapp.orders.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Order;

import java.util.List;

import rx.Observable;

public interface OrdersMVP {
    interface View extends IBaseView {
        void setOrders(List<Order> orders);
        void setTotals(List<String> totals);
    }

    interface Presenter extends IBasePresenter {
        void getOrders();
        void getTotals();
    }

    interface Model {
        Observable<List<Order>> getOrders();
        Observable<List<String>> getTotals();
    }
}
