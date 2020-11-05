package com.nawasoft.oneapp.login.forgetpassword.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class ForgetPasswordModule {

    @Provides
    ForgetPasswordMVP.Presenter providePresenter(ForgetPasswordMVP.Model model) {
        return new ForgetPasswordPresenter(model);
    }

    @Provides
    ForgetPasswordMVP.Model provideModel(IApiService service, ISharedPref sharedPref) {
        return new ForgetPasswordModel(service, sharedPref);
    }
}
