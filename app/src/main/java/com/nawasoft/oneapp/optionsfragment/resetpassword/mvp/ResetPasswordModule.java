package com.nawasoft.oneapp.optionsfragment.resetpassword.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class ResetPasswordModule {
    @Provides
    ResetPasswordMVP.Model provideResetModel(IApiService service, ISharedPref sharedPref) {
        return new ResetPasswordModel(service, sharedPref);
    }

    @Provides
    ResetPasswordMVP.Presenter provideResetPresenter(ResetPasswordMVP.Model model) {
        return new ResetPasswordPresenter(model);
    }
}
