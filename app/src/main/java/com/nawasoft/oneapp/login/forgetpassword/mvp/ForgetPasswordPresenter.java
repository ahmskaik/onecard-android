package com.nawasoft.oneapp.login.forgetpassword.mvp;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.login.ForgotPasswordResponse;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgetPasswordPresenter implements ForgetPasswordMVP.Presenter {

    private ForgetPasswordMVP.Model model;
    private ForgetPasswordMVP.View view;

    private Subscription forgetPasswrodSubscription;

    public ForgetPasswordPresenter(ForgetPasswordMVP.Model model) {
        this.model = model;
    }

    @Override
    public void forgetPassword() {
        String emailAddress = view.getEmail();
        if (emailAddress.isEmpty()) {
            view.showMessage(view.getString(R.string.fill_all_fields));
            return;
        }

        view.setLoading();
        forgetPasswrodSubscription = model.forgetPassword(emailAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForgotPasswordResponse>() {
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
                    public void onNext(ForgotPasswordResponse forgotPasswordResponse) {
                        int statusNumber = forgotPasswordResponse.getMeta().getStatusNumber();
                        if (statusNumber == 200) {
                            view.showAlert(view.getString(R.string.forget_password_message));
                            view.emailSent();
                        } else {
                            view.showAlert(forgotPasswordResponse.getData().getData().getMessage());
                        }
                    }
                });

    }

    @Override
    public void setView(IBaseView view) {
        this.view = (ForgetPasswordMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (forgetPasswrodSubscription != null && !forgetPasswrodSubscription.isUnsubscribed()) {
            forgetPasswrodSubscription.unsubscribe();
        }
    }
}
