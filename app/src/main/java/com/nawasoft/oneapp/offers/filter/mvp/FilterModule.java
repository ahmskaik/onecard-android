package com.nawasoft.oneapp.offers.filter.mvp;

import com.nawasoft.datalayer.cache.ICacheRepository;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class FilterModule {

    @Provides
    FilterMVP.Repository provideRepository(IApiService service,
                                           ISharedPref sharedPref,
                                           ICacheRepository cacheRepository) {
        return new FilterRepository(service, sharedPref, cacheRepository);
    }

    @Provides
    FilterMVP.Model provideModel(FilterMVP.Repository repository) {
        return new FilterModel(repository);
    }

    @Provides
    FilterMVP.Presenter providePresenter(FilterMVP.Model model) {
        return new FilterPresenter(model);
    }

}
