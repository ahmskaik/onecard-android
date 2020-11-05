package com.nawasoft.oneapp.editprofile.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.EditProfileRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetAllCountriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCountriesRequest;
import com.nawasoft.datalayer.http.apimodel.response.editprofile.EditProfileResponse;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.datalayer.model.User;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class EditProfileModel implements EditProfileMVP.Model {
    private IApiService service;
    private ISharedPref sharedPref;

    public EditProfileModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public User getSavedUser() {
        return sharedPref.getUser();
    }

    @Override
    public Observable<Country> getCountries() {
        return service.getAllCountries(
                new GetAllCountriesRequest(
                        ApiTags.GetAllCountries.getValue(),
                        sharedPref.getLanguage()
                )
        ).flatMap((Func1<List<Country>, Observable<Country>>) Observable::from);
    }

    @Override
    public Observable<EditProfileResponse> editProfile(
            String firstName,
            String lastName,
            String emailAddress,
            String address,
            String countryId,
            String phoneNumber,
            String birthday,
            String gender
    ) {
        return service.editProfile(
                new EditProfileRequest(
                        ApiTags.EditProfile.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId()),
                        firstName,
                        lastName,
                        emailAddress,
                        address,
                        countryId,
                        phoneNumber,
                        birthday,
                        gender
                )
        ).doOnNext(editProfileResponse -> {
            if (editProfileResponse.getMeta().getStatusNumber() == 200) {
                sharedPref.saveUser(editProfileResponse.getData().getData().get(0));
            }
        });
    }
}
