package com.nawasoft.oneapp.login.forgetpassword;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.oneapp.R;
import com.nawasoft.base.BaseActivity;
import com.nawasoft.oneapp.login.forgetpassword.mvp.ForgetPasswordMVP;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordMVP.View {

    @Inject
    ForgetPasswordMVP.Presenter presenter;

    @BindView(R.id.back_button)
    MaterialCardView backButton;
    @BindView(R.id.forget_email_input)
    EditText emailInput;
    @BindView(R.id.send_button)
    LinearLayout sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        setProgressBar(R.id.forget_password_progressBar);
        ButterKnife.bind(this);
        presenter.setView(this);
        backButton.setOnClickListener(view -> onBackPressed());

        sendButton.setOnClickListener(view -> presenter.forgetPassword());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    //view methods

    @Override
    public String getEmail() {
        return emailInput.getText().toString();
    }

    @Override
    public void emailSent() {
        emailInput.setText("");
    }
}
