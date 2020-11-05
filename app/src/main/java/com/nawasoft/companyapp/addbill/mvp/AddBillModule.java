package com.nawasoft.companyapp.addbill.mvp;

import com.nawasoft.datalayer.http.companyservice.IApiCompanyService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class AddBillModule {

    @Provides
    AddBillMVP.Model provideModel(IApiCompanyService service, ISharedPref sharedPref) {
        return new AddBillModel(service, sharedPref);
    }

    @Provides
    AddBillMVP.Presenter providePresenter(AddBillMVP.Model model) {
        return new AddBillPresenter(model);
    }
}
