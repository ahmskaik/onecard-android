package com.nawasoft.oneapp.payment.activecard.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class ActiveCardModule {

    @Provides
    ActiveCardMVP.Model provideModel(IApiService service, ISharedPref sharedPref) {
        return new ActiveCardModel(service, sharedPref);
    }

    @Provides
    ActiveCardMVP.Presenter providePresenter(ActiveCardMVP.Model model) {
        return new ActiveCardPresenter(model);
    }
}
