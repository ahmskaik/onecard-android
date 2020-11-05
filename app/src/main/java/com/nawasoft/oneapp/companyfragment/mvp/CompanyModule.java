package com.nawasoft.oneapp.companyfragment.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class CompanyModule {

    @Provides
    CompanyMVP.Model provideCompanyModel(IApiService service, ISharedPref sharedPref) {
        return new CompanyModel(service, sharedPref);
    }

    @Provides
    CompanyMVP.Presenter provideCompanyPresenter(CompanyMVP.Model model) {
        return new CompanyPresenter(model);
    }

}
