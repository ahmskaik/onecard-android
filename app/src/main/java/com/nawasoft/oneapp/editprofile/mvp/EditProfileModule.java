package com.nawasoft.oneapp.editprofile.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class EditProfileModule {

    @Provides
    EditProfileMVP.Model provideModel(IApiService service, ISharedPref sharedPref) {
        return new EditProfileModel(service, sharedPref);
    }

    @Provides
    EditProfileMVP.Presenter providePresenter(EditProfileMVP.Model model) {
        return new EditProfilePresenter(model);
    }
}
