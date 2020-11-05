package com.nawasoft.oneapp.editprofile.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.editprofile.EditProfileResponse;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.datalayer.model.User;

import java.util.List;

import rx.Observable;

public interface EditProfileMVP {
    interface View extends IBaseView {
        String getFirstName();
        String getLastName();
        String getEmailAddress();
        String getMobileNumber();
        String getBirthday();
        String getAddress();
        int getGender();
        int getCountryId();

        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setEmailAddress(String emailAddress);
        void setMobileNumber(String mobileNumber);
        void setGender(String gender);
        void setAddress(String address);
        void setBirthday(String birthday);
        void setCountryId(String countryId);

        void setCountries(List<Country> countries);

        void profileEdited();
    }

    interface Presenter extends IBasePresenter {
        void getSavedUser();
        void getCountries();
        void editProfile();
    }

    interface Model {
        User getSavedUser();
        Observable<Country> getCountries();
        Observable<EditProfileResponse> editProfile(
                String firstName,
                String lastName,
                String emailAddress,
                String address,
                String countryId,
                String phoneNumber,
                String birthday,
                String gender
        );
    }
}
