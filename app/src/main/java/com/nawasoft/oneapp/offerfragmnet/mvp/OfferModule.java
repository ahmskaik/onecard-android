package com.nawasoft.oneapp.offerfragmnet.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class OfferModule {
    @Provides
    OfferMVP.Presenter provideOfferPresenter(OfferMVP.Model model) {
        return new OfferPresenter(model);
    }

    @Provides
    OfferMVP.Model provideOfferModel(IApiService service, ISharedPref sharedPref) {
        return new OfferModel(service, sharedPref);
    }
}
