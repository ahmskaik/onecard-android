package com.nawasoft.oneapp.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.util.URL;
import com.nawasoft.oneapp.util.Util;
import com.nawasoft.base.BaseActivity;
import com.nawasoft.oneapp.login.LoginActivity;
import com.nawasoft.oneapp.signup.mvp.SignUpMVP;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class SignUpActivity extends BaseActivity implements SignUpMVP.View {

    @Inject
    SignUpMVP.Presenter presenter;

    @BindView(R.id.signUp_email_input)
    EditText emailAddress;
    @BindView(R.id.signUp_name_input)
    EditText name;
    @BindView(R.id.signUp_mobile_input)
    EditText phone;
    @BindView(R.id.signUp_password_input)
    EditText password;
    @BindView(R.id.signUp_rePassword_input)
    EditText rePassword;
    @BindView(R.id.privacy_checkbox)
    AppCompatCheckBox privacyCheckbox;
    @BindView(R.id.privacy_text)
    TextView privacyText;
    @BindView(R.id.signUp_button)
    MaterialCardView signUpButton;
    @BindView(R.id.signUp_button_layout)
    LinearLayout signUpButtonLayout;
    @BindView(R.id.countries_spinner)
    Spinner spinner;

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    private List<Country> countries = new ArrayList<>();
    private ArrayAdapter<Country> countriesAdapter;

    private String countryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setProgressBar(R.id.signUp_progressBar);

        initializePrivacySpannable(privacyText);
        initializeCountriesAdapter();

        presenter.setView(this);
        presenter.getCountries();


        privacyCheckbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                setSignUpButtonEnable();
            else
                setSignUpButtonDisable();
        });
        setSignUpButtonDisable();
    }


    @OnClick({R.id.create_account_login_button, R.id.signUp_button})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_account_login_button:
                Util.goToActivity(this, LoginActivity.class, null);
                finish();
                break;
            case R.id.signUp_button:
                presenter.signUp();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    private void initializeCountriesAdapter() {
        countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countries);
        spinner.setAdapter(countriesAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                countryId = countries.get(position).getCountryId();
                System.out.println("selected = " + countryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void initializePrivacySpannable(TextView textView) {
        String leadingPrivacyText = getString(R.string.agree_to_one);
        String privacyPolicy = getString(R.string.privacy_policy);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(leadingPrivacyText);
        builder.append(" ");
        builder.append(privacyPolicy);
        builder.setSpan(
                new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View view) {
                        System.out.println("Clicked");
                        Util.openBrowser(SignUpActivity.this, URL.PRIVACY_POLICY.toString());
                    }
                },
                leadingPrivacyText.length() + 1,
                builder.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        textView.setText(builder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setSignUpButtonEnable() {
        signUpButton.setEnabled(true);
        signUpButtonLayout.setBackground(getDrawable(R.drawable.horizontal_gradient));
    }

    private void setSignUpButtonDisable() {
        signUpButton.setEnabled(false);
        signUpButtonLayout.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }


    // view methods

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getEmailAddress() {
        return emailAddress.getText().toString();
    }

    @Override
    public String getPhone() {
        return phone.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getRePassword() {
        return rePassword.getText().toString();
    }

    @Override
    public String getCountryId() {
        return countryId;
    }

    @Override
    public void setCountries(List<Country> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        countriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSignedUp() {
        showMessage(getString(R.string.successfully_registered));
        Intent intent = new Intent();
        Bundle data = new Bundle();
        data.putString(EMAIL, getEmailAddress());
        data.putString(PASSWORD, getPassword());
        intent.putExtras(data);
        setResult(RESULT_OK, intent);
        finish();
    }
}
