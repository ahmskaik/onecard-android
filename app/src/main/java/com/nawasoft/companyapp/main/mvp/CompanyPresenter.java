package com.nawasoft.companyapp.main.mvp;

import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.barcode.CheckBarCodeData;
import com.nawasoft.datalayer.http.apimodel.response.barcode.CheckBarcodeResponse;
import com.nawasoft.oneapp.R;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompanyPresenter implements CompanyMainMVP.Presenter {

    private CompanyMainMVP.Model model;
    private CompanyMainMVP.View view;

    private Subscription checkQrCodeSubscription;

    public CompanyPresenter(CompanyMainMVP.Model model) {
        this.model = model;
    }

    @Override
    public void checkQrCode(String serialNumber) {
        view.setLoading();
        checkQrCodeSubscription = model.checkQrCode(serialNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckBarcodeResponse>() {
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
                    public void onNext(CheckBarcodeResponse checkBarcodeResponse) {
                        int statusNumber = checkBarcodeResponse.getMeta().getStatusNumber();
                        if (statusNumber == 200) {
                            view.setCorrectQrCode(checkBarcodeResponse.getData().getMemberId());
                        } else {
                            CheckBarCodeData data = checkBarcodeResponse.getData();
                            if (data != null && data.getMemberId() != null && !data.getMemberId().isEmpty()) {
                                view.showAlert(data.getMemberId());
                            } else {
                                view.showAlert(view.getString(R.string.wrong_barcode));
                            }
                        }
                    }
                });
    }

    @Override
    public void logout() {
        model.logout();
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (CompanyMainMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (checkQrCodeSubscription != null && !checkQrCodeSubscription.isUnsubscribed())
            checkQrCodeSubscription.unsubscribe();
    }
}
