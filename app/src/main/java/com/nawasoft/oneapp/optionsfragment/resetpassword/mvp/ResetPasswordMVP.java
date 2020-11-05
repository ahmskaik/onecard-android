package com.nawasoft.oneapp.optionsfragment.resetpassword.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.profile.ResetPasswordResponse;

import rx.Observable;

public interface ResetPasswordMVP {
    interface View extends IBaseView {
        String getOldPassword();
        String getNewPassword();
        String getConfirmPassword();
        void setPasswordChanged();
    }

    interface Presenter extends IBasePresenter {
        void resetPassword();
    }

    interface Model {
        Observable<ResetPasswordResponse> resetPassword(String oldPassword, String newPassword);
    }
}
