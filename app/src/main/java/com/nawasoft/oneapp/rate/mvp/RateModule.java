package com.nawasoft.oneapp.rate.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class RateModule {

    @Provides
    RateMVP.Model provideModel(IApiService service, ISharedPref sharedPref) {
        return new RateModel(service, sharedPref);
    }

    @Provides
    RateMVP.Presenter providePresenter(RateMVP.Model model) {
        return new RatePresenter(model);
    }
}
