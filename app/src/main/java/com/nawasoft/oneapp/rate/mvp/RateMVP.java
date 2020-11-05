package com.nawasoft.oneapp.rate.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.rate.MakeRateResponse;

import rx.Observable;

public interface RateMVP {
    interface View extends IBaseView {
        String getOrderId();
        String getComment();
        int getRate();
        void setRated();
    }

    interface Presenter extends IBasePresenter {
        void makeRate();
    }

    interface Model {
        Observable<MakeRateResponse> makeRate(String orderId, int rate, String comment);
    }
}
