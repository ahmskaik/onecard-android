package com.nawasoft.oneapp.rate.mvp;

import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.rate.MakeRateResponse;
import com.nawasoft.oneapp.R;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RatePresenter implements RateMVP.Presenter {
    private RateMVP.Model model;
    private RateMVP.View  view;

    private Subscription makeRateSubscription;

    public RatePresenter(RateMVP.Model model) {
        this.model = model;
    }

    @Override
    public void makeRate() {
        view.setLoading();
        makeRateSubscription = model.makeRate(view.getOrderId(), view.getRate(), view.getComment())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MakeRateResponse>() {
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
                    public void onNext(MakeRateResponse makeRateResponse) {
                        int statusNumber = makeRateResponse.getMeta().getStatusNumber();
                        if (statusNumber == 200) {
                            view.setRated();
                            view.showAlert(view.getString(R.string.review_submitted));
                        } else {
                            view.showAlert(view.getString(R.string.something_went_wrong));
                        }
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (RateMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (makeRateSubscription != null && !makeRateSubscription.isUnsubscribed()) {
            makeRateSubscription.unsubscribe();
        }
    }
}
