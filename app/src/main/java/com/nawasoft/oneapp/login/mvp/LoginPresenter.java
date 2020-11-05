package com.nawasoft.oneapp.login.mvp;

import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.login.LoginResponse;
import com.nawasoft.datalayer.model.AccountType;
import com.nawasoft.oneapp.R;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter implements LoginMVP.Presenter {

    private LoginMVP.Model model;
    private LoginMVP.View view;

    private Subscription loginSubscription;


    public LoginPresenter(LoginMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (LoginMVP.View) view;
    }


    @Override
    public void login() {
        view.setLoading();
        loginSubscription = model.login(view.getUsername(), view.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showAlert(view.getString(R.string.no_internt_connection));
                        view.setLoaded();
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if (loginResponse.getMeta().getStatusNumber() == 200) {
                            if (model.getAccountType() == AccountType.MEMBER)
                                view.setLoggedIn();
                            else
                                view.setLoggedInAsCompany();
                        } else {
                            view.showAlert(loginResponse.getData().getData().getMessage());
                        }
                    }
                });
    }

    @Override
    public void cancelCalls() {
        if (loginSubscription != null && !loginSubscription.isUnsubscribed()) {
            loginSubscription.unsubscribe();
        }
    }
}
