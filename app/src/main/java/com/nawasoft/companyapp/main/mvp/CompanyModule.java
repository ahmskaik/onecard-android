package com.nawasoft.companyapp.main.mvp;

import com.nawasoft.datalayer.http.companyservice.IApiCompanyService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class CompanyModule {

    @Provides
    CompanyMainMVP.Model provideModel(IApiCompanyService service, ISharedPref sharedPref) {
        return new CompanyModel(service, sharedPref);
    }

    @Provides
    CompanyMainMVP.Presenter providePresenter(CompanyMainMVP.Model model) {
        return new CompanyPresenter(model);
    }
}
