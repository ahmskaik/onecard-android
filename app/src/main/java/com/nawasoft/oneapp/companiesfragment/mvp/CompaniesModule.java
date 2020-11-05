package com.nawasoft.oneapp.companiesfragment.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class CompaniesModule {

    @Provides
    CompaniesMVP.Presenter provideCompaniesPresenter(CompaniesMVP.Model model) {
        return new CompaniesPresenter(model);
    }

    @Provides
    CompaniesMVP.Model provideCompaniesModel(CompaniesMVP.Repository repository) {
        return new CompaniesModel(repository);
    }

    @Provides
    CompaniesMVP.Repository provideCompaniesRepository(IApiService service, ISharedPref sharedPref) {
        return new CompaniesRepository(service, sharedPref);
    }

}
