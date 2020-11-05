package com.nawasoft.oneapp.login.forgetpassword.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.ForgotPasswordRequest;
import com.nawasoft.datalayer.http.apimodel.response.login.ForgotPasswordResponse;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;

public class ForgetPasswordModel implements ForgetPasswordMVP.Model {

    private IApiService service;
    private ISharedPref sharedPref;

    public ForgetPasswordModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<ForgotPasswordResponse> forgetPassword(String emailAddress) {
        return service.forgetPassword(
                new ForgotPasswordRequest(
                        ApiTags.ForgotPassword.getValue(),
                        sharedPref.getLanguage(),
                        emailAddress
                )
        );
    }
}
