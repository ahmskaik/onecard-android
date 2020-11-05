package com.nawasoft.oneapp.payment.activecard.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.activecard.ActiveCardResponse;

import rx.Observable;

public interface ActiveCardMVP {

    interface View extends IBaseView {
        void setCardActivated();
        String getSerialNumber();
        String getPassword();
    }

    interface Presenter extends IBasePresenter {
        void activeCard();
    }

    interface Model {
        Observable<ActiveCardResponse> activeCard(String serialNumber, String password);
    }
}
