package com.nawasoft.oneapp.tickets.mytickets.mvp;

import com.nawasoft.datalayer.database.IDatabaseService;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class MyTicketsModule {

    @Provides
    MyTicketsMVP.Repository provideRepository(IApiService service, ISharedPref sharedPref, IDatabaseService databaseService) {
        return new MyTicketsRepository(service, sharedPref, databaseService);
    }

    @Provides
    MyTicketsMVP.Model provideModel(MyTicketsMVP.Repository repository) {
        return new MyTicketsModel(repository);
    }

    @Provides
    MyTicketsMVP.Presenter providePresenter(MyTicketsMVP.Model model) {
        return new MyTicketPresenter(model);
    }

}
