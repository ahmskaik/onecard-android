package com.nawasoft.oneapp.optionsfragment.resetpassword;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.BaseActivity;
import com.nawasoft.oneapp.optionsfragment.resetpassword.mvp.ResetPasswordMVP;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordMVP.View {

    @Inject
    ResetPasswordMVP.Presenter presenter;

    @BindView(R.id.reset_old_password_input)
    EditText oldPassword;
    @BindView(R.id.reset_new_password_input)
    EditText newPassword;
    @BindView(R.id.reset_confirm_password_input)
    EditText confirmPassword;
    @BindView(R.id.reset_password_button)
    LinearLayout resetPasswordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        setProgressBar(R.id.reset_password_progressBar);
        presenter.setView(this);

        resetPasswordButton.setOnClickListener(view -> presenter.resetPassword());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    // view methods
    @Override
    public String getOldPassword() {
        return oldPassword.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return newPassword.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword.getText().toString();
    }

    @Override
    public void setPasswordChanged() {
        showMessage(getString(R.string.password_changed));
        finish();
    }

}
