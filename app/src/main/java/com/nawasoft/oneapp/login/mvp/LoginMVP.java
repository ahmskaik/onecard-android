package com.nawasoft.oneapp.login.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.login.LoginResponse;
import com.nawasoft.datalayer.model.AccountType;

import rx.Observable;

public interface LoginMVP {
    interface View extends IBaseView {
        void setLoggedIn();
        void setLoggedInAsCompany();
        String getUsername();
        String getPassword();
    }

    interface Presenter extends IBasePresenter {
        void login();
    }

    interface Model {
        Observable<LoginResponse> login(String username, String password);
        AccountType getAccountType();
    }
}
