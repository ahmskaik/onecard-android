package com.nawasoft.companyapp.addbill.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.model.CompanyCard;
import com.nawasoft.datalayer.model.Currency;

import java.util.List;

import rx.Observable;

public interface AddBillMVP {

    interface View extends IBaseView {
        void setCurrencies(List<Currency> currencies);
        void setCompanyCards(List<CompanyCard> companyCards);
        void billAdded();
        String getBillValue();
        String getSaleValue();
        String getCurrencyId();
        String getOfferId();
        String getMemberId();
    }

    interface Presenter extends IBasePresenter {
        void getCurrencies();
        void getCompanyCards();
        void addBill();
    }

    interface Model {
        Observable<List<Currency>> getCurrencies();
        Observable<List<CompanyCard>> getCompanyCards();
        Observable<BaseResponse> addBill(
                String memberId,
                String billValue,
                String saleValue,
                String currencyId,
                String offerId
        );
    }
}
