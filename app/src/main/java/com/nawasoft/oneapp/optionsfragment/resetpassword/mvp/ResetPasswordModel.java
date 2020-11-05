package com.nawasoft.oneapp.optionsfragment.resetpassword.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.ResetPasswordRequest;
import com.nawasoft.datalayer.http.apimodel.response.profile.ResetPasswordResponse;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;

public class ResetPasswordModel implements ResetPasswordMVP.Model {

    private IApiService service;
    private ISharedPref sharedPref;

    public ResetPasswordModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<ResetPasswordResponse> resetPassword(String oldPassword, String newPassword) {
        return service.resetPassword(
                new ResetPasswordRequest(
                        ApiTags.ResetPassword.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId()),
                        oldPassword,
                        newPassword
                )
        );
    }
}
