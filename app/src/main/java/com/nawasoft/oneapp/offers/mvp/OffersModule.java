package com.nawasoft.oneapp.offers.mvp;

import com.nawasoft.datalayer.cache.ICacheRepository;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class OffersModule {
    @Provides
    OffersMVP.Repository provideOffersRepository(IApiService service, ISharedPref sharedPref, ICacheRepository cacheRepository) {
        return new OffersRepository(service, sharedPref, cacheRepository);
    }

    @Provides
    OffersMVP.Model provideOffersModel(OffersMVP.Repository repository) {
        return new OffersModel(repository);
    }

    @Provides
    OffersMVP.Presenter provideOfferPresenter(OffersMVP.Model model) {
        return new OffersPresenter(model);
    }
}
