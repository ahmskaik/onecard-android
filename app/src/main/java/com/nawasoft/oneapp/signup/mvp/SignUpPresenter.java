package com.nawasoft.oneapp.signup.mvp;

import com.nawasoft.datalayer.model.Country;
import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.register.RegisterResponse;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignUpPresenter implements SignUpMVP.Presenter {

    private SignUpMVP.Model model;
    private SignUpMVP.View view;

    private Subscription registerSubscription;
    private Subscription countriesSubscription;

    public SignUpPresenter(SignUpMVP.Model model) {
        this.model = model;
    }


    @Override
    public void getCountries() {
        countriesSubscription = model.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Country>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Country> countries) {
                        view.setCountries(countries);
                    }
                });
    }

    @Override
    public void signUp() {
        if (!checkFields(
                view.getName(),
                view.getEmailAddress(),
                view.getPassword(),
                view.getRePassword(),
                view.getPhone(),
                view.getCountryId()
        )) {
            view.showMessage(view.getString(R.string.fill_all_fields));
            return;
        }

        if (!view.getPassword().equals(view.getRePassword())) {
            view.showMessage(view.getString(R.string.password_doesnot_match));
            return;
        }

        view.setLoading();

        registerSubscription = model.signUp(view.getName(), view.getEmailAddress(), view.getPhone(), view.getPassword(), view.getCountryId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showAlert(view.getString(R.string.no_internt_connection));
                        view.setLoaded();
                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        if (registerResponse.getMeta().getStatusNumber() == 200) {
                            view.setSignedUp();
                        } else {
                            view.showAlert(registerResponse.getData().getData().getDataMessage());
                        }
                    }
                });
    }

    private boolean checkFields(String...fields) {
        for (String field: fields) {
            if (field.isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (SignUpMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (registerSubscription != null && !registerSubscription.isUnsubscribed()) {
            registerSubscription.unsubscribe();
        }
        if (countriesSubscription != null && !countriesSubscription.isUnsubscribed()) {
            countriesSubscription.unsubscribe();
        }
    }
}
