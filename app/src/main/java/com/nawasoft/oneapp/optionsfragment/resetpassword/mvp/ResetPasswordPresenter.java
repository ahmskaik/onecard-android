package com.nawasoft.oneapp.optionsfragment.resetpassword.mvp;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.profile.ResetPasswordResponse;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ResetPasswordPresenter implements ResetPasswordMVP.Presenter {
    private ResetPasswordMVP.Model model;
    private ResetPasswordMVP.View view;

    private Subscription resetPasswordSubscriptions;

    public ResetPasswordPresenter(ResetPasswordMVP.Model model) {
        this.model = model;
    }

    @Override
    public void resetPassword() {
        String oldPassword = view.getOldPassword();
        String newPassword = view.getNewPassword();
        String confirmPassword = view.getConfirmPassword();
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            view.showMessage(view.getString(R.string.fill_all_fields));
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            view.showMessage(view.getString(R.string.no_match));
            return;
        }

        view.setLoading();
        resetPasswordSubscriptions = model.resetPassword(oldPassword, newPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResetPasswordResponse>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(ResetPasswordResponse response) {
                        int status = response.getMeta().getStatusNumber();
                        if (status == 213)
                            view.showAlert(response.getData().getData().getMessage());
                        else if (status == 200) {
                            view.setPasswordChanged();
                        }
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (ResetPasswordMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (resetPasswordSubscriptions != null && !resetPasswordSubscriptions.isUnsubscribed())
            resetPasswordSubscriptions.unsubscribe();
    }
}
