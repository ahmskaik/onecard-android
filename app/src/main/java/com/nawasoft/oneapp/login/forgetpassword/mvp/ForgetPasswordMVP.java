package com.nawasoft.oneapp.login.forgetpassword.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.login.ForgotPasswordResponse;

import rx.Observable;

public interface ForgetPasswordMVP {
    interface View extends IBaseView {
        String getEmail();
        void emailSent(); // email has been sent
    }

    interface Presenter extends IBasePresenter {
        void forgetPassword();
    }

    interface Model {
        Observable<ForgotPasswordResponse> forgetPassword(String emailAddress);
    }
}
