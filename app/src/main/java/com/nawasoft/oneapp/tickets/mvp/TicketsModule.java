package com.nawasoft.oneapp.tickets.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class TicketsModule {

    @Provides
    TicketsMVP.Model provideModel(IApiService service, ISharedPref sharedPref) {
        return new TicketsModel(service, sharedPref);
    }

    @Provides
    TicketsMVP.Presenter providePresenter(TicketsMVP.Model model) {
        return new TicketPresenter(model);
    }

}
