package com.nawasoft.oneapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.nawasoft.base.BaseActivity;
import com.nawasoft.companyapp.main.CompanyMainActivity;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.login.forgetpassword.ForgetPasswordActivity;
import com.nawasoft.oneapp.login.mvp.LoginMVP;
import com.nawasoft.oneapp.signup.SignUpActivity;
import com.nawasoft.oneapp.util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class LoginActivity extends BaseActivity implements LoginMVP.View {

    @Inject
    LoginMVP.Presenter presenter;

    @BindView(R.id.login_name_input)
    EditText emailAddress;
    @BindView(R.id.login_password_input)
    EditText password;

    public static int LOGGED_IN_CODE = 772;
    public static int SIGN_UP_CODE = 991;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setProgressBar(R.id.login_progressBar);
        ButterKnife.bind(this);
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    @OnClick({R.id.login_create_account_button, R.id.login_button, R.id.forget_password_button})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_create_account_button:
//                Util.goToActivity(this, SignUpActivity.class, null);
//                finish();
                startActivityForResult(new Intent(this, SignUpActivity.class), SIGN_UP_CODE);
                break;
            case R.id.login_button:
                presenter.login();
                break;
            case R.id.forget_password_button:
                Util.goToActivity(this, ForgetPasswordActivity.class, null);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SIGN_UP_CODE && resultCode == RESULT_OK && data != null) {
            Bundle userInfo = data.getExtras();
            if (userInfo != null) {
                emailAddress.setText(userInfo.getString(SignUpActivity.EMAIL));
                password.setText(userInfo.getString(SignUpActivity.PASSWORD));
                presenter.login();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setLoggedIn() {
        setResult(LOGGED_IN_CODE);
        finish();
    }

    @Override
    public void setLoggedInAsCompany() {
        Intent intent = new Intent(this, CompanyMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public String getUsername() {
        return emailAddress.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

}
