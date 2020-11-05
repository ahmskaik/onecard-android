package com.nawasoft.oneapp.signup.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.register.RegisterResponse;
import com.nawasoft.datalayer.model.Country;

import java.util.List;

import rx.Observable;

public interface SignUpMVP {
    interface View extends IBaseView {
        String getName();
        String getEmailAddress();
        String getPhone();
        String getPassword();
        String getRePassword();
        String getCountryId();
        void setSignedUp();
        void setCountries(List<Country> countries);
    }

    interface Presenter extends IBasePresenter {
        void getCountries();
        void signUp();
    }

    interface Model {
        Observable<RegisterResponse> signUp(
                String name,
                String emailAddress,
                String phone,
                String password,
                String countryId
        );

        Observable<List<Country>> getCountries();
    }

}
