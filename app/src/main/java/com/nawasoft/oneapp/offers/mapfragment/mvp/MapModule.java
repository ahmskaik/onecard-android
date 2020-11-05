package com.nawasoft.oneapp.offers.mapfragment.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class MapModule {
    @Provides
    MapMVP.Model provideMapModel(IApiService service, ISharedPref sharedPref) {
        return new MapModel(service, sharedPref);
    }

    @Provides
    MapMVP.Presenter provideMapPresenter(MapMVP.Model model) {
        return new MapPresenter(model);
    }
}
