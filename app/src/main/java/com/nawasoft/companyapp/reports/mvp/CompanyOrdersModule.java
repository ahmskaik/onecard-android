package com.nawasoft.companyapp.reports.mvp;

import com.nawasoft.datalayer.http.companyservice.IApiCompanyService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class CompanyOrdersModule {
    @Provides
    CompanyOrdersMVP.Model provideModel(IApiCompanyService service, ISharedPref sharedPref) {
        return new CompanyOrdersModel(service, sharedPref);
    }

    @Provides
    CompanyOrdersMVP.Presenter providePresenter(CompanyOrdersMVP.Model model) {
        return new CompanyOrdersPresenter(model);
    }
}
