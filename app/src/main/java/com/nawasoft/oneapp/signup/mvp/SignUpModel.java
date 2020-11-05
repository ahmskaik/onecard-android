package com.nawasoft.oneapp.signup.mvp;


import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetAllCountriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.RegisterRequest;
import com.nawasoft.datalayer.http.apimodel.response.register.RegisterResponse;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.List;

import rx.Observable;

public class SignUpModel implements SignUpMVP.Model {

    private IApiService service;
    private ISharedPref sharedPref;

    public SignUpModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<RegisterResponse> signUp(String name, String emailAddress, String phone, String password, String countryId) {
        return service.register(new RegisterRequest(
                ApiTags.Register.getValue(),
                sharedPref.getLanguage(),
                name,
                phone,
                emailAddress,
                password,
                countryId,
                sharedPref.getToken()
        ));
    }

    @Override
    public Observable<List<Country>> getCountries() {
        return service.getAllCountries(
                new GetAllCountriesRequest(ApiTags.GetAllCountries.getValue(), sharedPref.getLanguage())
        );
    }
}
