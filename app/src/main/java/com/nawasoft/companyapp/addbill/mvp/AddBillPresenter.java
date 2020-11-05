package com.nawasoft.companyapp.addbill.mvp;

import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.model.CompanyCard;
import com.nawasoft.datalayer.model.Currency;
import com.nawasoft.oneapp.R;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddBillPresenter implements AddBillMVP.Presenter {
    private AddBillMVP.Model model;
    private AddBillMVP.View view;

    private Subscription getCurrenciesSubscription;
    private Subscription getCompanyCardsSubscription;
    private Subscription addBillSubscription;

    public AddBillPresenter(AddBillMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getCurrencies() {
        getCurrenciesSubscription = model.getCurrencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Currency>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(List<Currency> currencies) {
                        view.setCurrencies(currencies);
                    }
                });
    }

    @Override
    public void getCompanyCards() {
        getCompanyCardsSubscription = model.getCompanyCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CompanyCard>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(List<CompanyCard> companyCards) {
                        view.setCompanyCards(companyCards);
                    }
                });
    }

    @Override
    public void addBill() {
        String memberId = view.getMemberId();
        String billValue = view.getBillValue();
        String saleValue = view.getSaleValue();
        String currencyId = view.getCurrencyId();
        String offerId =  view.getOfferId();
        if (billValue.isEmpty() || saleValue.isEmpty() || currencyId.isEmpty()) {
            view.showMessage(view.getString(R.string.fill_all_fields));
            return;
        }
        if (offerId.isEmpty()) {
            view.showMessage(view.getString(R.string.choose_offer));
            return;
        }
        if (Integer.valueOf(billValue) < Integer.valueOf(saleValue)) {
            view.showMessage(view.getString(R.string.bill_value_message));
            return;
        }

        view.setLoading();

        addBillSubscription = model.addBill(
                memberId,
                billValue,
                saleValue,
                currencyId,
                offerId
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
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
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse.getMeta().getStatusNumber() == 200) {
                            view.showAlert(view.getString(R.string.bill_added_successfully));
                            view.billAdded();
                        } else {
                            view.showAlert(view.getString(R.string.something_went_wrong));
                        }
                    }
                });


    }

    @Override
    public void setView(IBaseView view) {
        this.view = (AddBillMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getCurrenciesSubscription != null && !getCurrenciesSubscription.isUnsubscribed())
            getCurrenciesSubscription.unsubscribe();
        if (getCompanyCardsSubscription != null && !getCompanyCardsSubscription.isUnsubscribed())
            getCompanyCardsSubscription.unsubscribe();
        if (addBillSubscription != null && !addBillSubscription.isUnsubscribed())
            addBillSubscription.unsubscribe();
    }
}
