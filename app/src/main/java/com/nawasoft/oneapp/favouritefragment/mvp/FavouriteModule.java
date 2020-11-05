package com.nawasoft.oneapp.favouritefragment.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouriteModule {
    @Provides
    FavouriteMVP.Presenter provideFavouritePresenter(FavouriteMVP.Model model) {
        return new FavouritePresenter(model);
    }

    @Provides
    FavouriteMVP.Model provideFavouriteModel(IApiService service, ISharedPref sharedPref) {
        return new FavouriteModel(service, sharedPref);
    }
}
