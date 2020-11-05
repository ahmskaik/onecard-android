package com.nawasoft.companyapp.reports.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.oreders.GetAllTotalsResponse;
import com.nawasoft.datalayer.model.CompanyOrder;

import java.util.List;

import rx.Observable;

public interface CompanyOrdersMVP {
    interface View extends IBaseView {
        void setOrders(List<CompanyOrder> companyOrders);
        void setTotals(List<String> totals);
    }

    interface Presenter extends IBasePresenter {
        void getCompanyOrders();
        void getAllTotals();
    }

    interface Model {
        Observable<List<CompanyOrder>> getCompanyOrders();
        Observable<List<String>> getAllTotals();
    }
}
