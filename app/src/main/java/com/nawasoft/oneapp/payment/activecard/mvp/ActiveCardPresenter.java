package com.nawasoft.oneapp.payment.activecard.mvp;

import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.activecard.ActiveCardResponse;
import com.nawasoft.oneapp.R;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ActiveCardPresenter implements ActiveCardMVP.Presenter {

    private ActiveCardMVP.Model model;
    private ActiveCardMVP.View view;

    private Subscription activeCardSubscriptions;

    public ActiveCardPresenter(ActiveCardMVP.Model model) {
        this.model = model;
    }

    @Override
    public void activeCard() {
        String serialNumber = view.getSerialNumber();
        String password = view.getPassword();
        if (serialNumber.isEmpty() || password.isEmpty()) {
            view.showMessage(view.getString(R.string.fill_all_fields));
            return;
        }

        view.setLoading();
        activeCardSubscriptions = model.activeCard(serialNumber, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ActiveCardResponse>() {
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
                    public void onNext(ActiveCardResponse activeCardResponse) {
                        int statusNumber = activeCardResponse.getMeta().getStatusNumber();
                        if (statusNumber == 200) {
                            view.setCardActivated();
                        } else {
                            view.showAlert(activeCardResponse.getData().getData().getMessage());
                        }
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (ActiveCardMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (activeCardSubscriptions != null && !activeCardSubscriptions.isUnsubscribed())
            activeCardSubscriptions.unsubscribe();
    }
}
