package com.nawasoft.companyapp.main.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.barcode.CheckBarcodeResponse;

import rx.Observable;

public interface CompanyMainMVP {
    interface View extends IBaseView {
        void setCorrectQrCode(String memberId);
    }

    interface Presenter extends IBasePresenter {
        void checkQrCode(String serialNumber);
        void logout();
    }

    interface Model {
        Observable<CheckBarcodeResponse> checkQrCode(String serialNumber);
        void logout();
    }
}
