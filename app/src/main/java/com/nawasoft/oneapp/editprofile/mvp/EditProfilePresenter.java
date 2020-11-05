package com.nawasoft.oneapp.editprofile.mvp;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.editprofile.EditProfileResponse;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.datalayer.model.User;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EditProfilePresenter implements EditProfileMVP.Presenter {

    private EditProfileMVP.Model model;
    private EditProfileMVP.View view;

    private Subscription getCountriesSubscription;
    private Subscription editProfileSubscription;

    public EditProfilePresenter(EditProfileMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getSavedUser() {
        User user = model.getSavedUser();
        view.setFirstName(user.getFirstName());
        view.setLastName(user.getLastName());
        view.setEmailAddress(user.getEmailAddress());
        view.setMobileNumber(user.getPhoneNumber());
        view.setGender(user.getGender());
        view.setBirthday(user.getBirthday());
        view.setCountryId(user.getCountryId());
        view.setAddress(user.getAddress());
    }

    @Override
    public void getCountries() {
        List<Country> countries = new ArrayList<>();
        getCountriesSubscription = model.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Country>() {
                    @Override
                    public void onCompleted() {
                        view.setCountries(countries);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Country country) {
                        countries.add(country);
                    }
                });
    }


    @Override
    public void editProfile() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String emailAddress = view.getEmailAddress();
        String mobileNumber = view.getMobileNumber();
        String address = view.getAddress();
        String birthday = view.getBirthday();
        int gender = view.getGender();
        int countryId = view.getCountryId();

        if (firstName.isEmpty() ||
                lastName.isEmpty() ||
                emailAddress.isEmpty() ||
                mobileNumber.isEmpty() ||
                birthday.isEmpty() ||
                countryId == -1) {
            view.showMessage(view.getString(R.string.fill_all_fields));
            return;
        }

        view.setLoading();

        editProfileSubscription = model.editProfile(
                firstName,
                lastName,
                emailAddress,
                address,
                String.valueOf(countryId),
                mobileNumber,
                birthday,
                String.valueOf(gender)
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EditProfileResponse>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showMessage(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(EditProfileResponse editProfileResponse) {
                        int statusNumber = editProfileResponse.getMeta().getStatusNumber();
                        if (statusNumber == 200) {
                            view.profileEdited();
                        } else {
                            view.showAlert(editProfileResponse.getData().getData().get(0).getMessage());
                        }
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (EditProfileMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getCountriesSubscription != null && !getCountriesSubscription.isUnsubscribed())
            getCountriesSubscription.unsubscribe();
        if (editProfileSubscription != null && !editProfileSubscription.isUnsubscribed())
            editProfileSubscription.unsubscribe();
    }
}
