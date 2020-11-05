package com.nawasoft.oneapp.login.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    LoginMVP.Presenter provideLoginPresenter(LoginMVP.Model model) {
        return new LoginPresenter(model);
    }

    @Provides
    LoginMVP.Model provideLoginModel(IApiService service, ISharedPref sharedPref) {
        return new LoginModel(service, sharedPref);
    }
}
