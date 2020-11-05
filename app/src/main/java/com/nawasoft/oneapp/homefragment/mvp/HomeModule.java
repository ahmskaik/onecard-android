package com.nawasoft.oneapp.homefragment.mvp;

import com.nawasoft.datalayer.cache.ICacheRepository;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {


    @Provides
    HomeMVP.Repository provideHomeRepository(IApiService service, ISharedPref sharedPref, ICacheRepository cacheRepository) {
        return new HomeRepository(service, sharedPref, cacheRepository);
    }

    @Provides
    HomeMVP.Model provideHomeModel(HomeMVP.Repository repository) {
        return new HomeModel(repository);
    }

    @Provides
    HomeMVP.Presenter provideHomePresenter(HomeMVP.Model model) {
        return new HomePresenter(model);
    }
}
