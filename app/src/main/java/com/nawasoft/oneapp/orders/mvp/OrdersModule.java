package com.nawasoft.oneapp.orders.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class OrdersModule {

    @Provides
    OrdersMVP.Model provideModel(IApiService service, ISharedPref sharedPref) {
        return new OrdersModel(service, sharedPref);
    }

    @Provides
    OrdersMVP.Presenter providePresenter(OrdersMVP.Model model) {
        return new OrdersPresenter(model);
    }
}
