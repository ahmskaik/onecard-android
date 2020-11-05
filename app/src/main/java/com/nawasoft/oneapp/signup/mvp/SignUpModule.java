package com.nawasoft.oneapp.signup.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpModule {

    @Provides
    SignUpMVP.Presenter provideSignUpPresenter(SignUpMVP.Model model) {
        return new SignUpPresenter(model);
    }

    @Provides
    SignUpMVP.Model provideSignUpModel(IApiService service, ISharedPref sharedPref) {
        return new SignUpModel(service, sharedPref);
    }
}
