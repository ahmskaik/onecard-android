package com.nawasoft.di;

import com.nawasoft.companyapp.addbill.AddBillActivity;
import com.nawasoft.companyapp.addbill.mvp.AddBillModule;
import com.nawasoft.companyapp.main.CompanyMainActivity;
import com.nawasoft.companyapp.main.mvp.CompanyModule;
import com.nawasoft.companyapp.reports.CompanyOrdersActivity;
import com.nawasoft.companyapp.reports.mvp.CompanyOrdersModule;
import com.nawasoft.oneapp.main.MainActivity;
import com.nawasoft.oneapp.login.LoginActivity;
import com.nawasoft.oneapp.login.forgetpassword.ForgetPasswordActivity;
import com.nawasoft.oneapp.login.forgetpassword.mvp.ForgetPasswordModule;
import com.nawasoft.oneapp.login.mvp.LoginModule;
import com.nawasoft.oneapp.main.MainModule;
import com.nawasoft.oneapp.optionsfragment.resetpassword.ResetPasswordActivity;
import com.nawasoft.oneapp.optionsfragment.resetpassword.mvp.ResetPasswordModule;
import com.nawasoft.oneapp.signup.SignUpActivity;
import com.nawasoft.oneapp.signup.mvp.SignUpModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = {SignUpModule.class})
    abstract SignUpActivity contributeSignUpActivity();

    @ContributesAndroidInjector(modules = {ResetPasswordModule.class})
    abstract ResetPasswordActivity contributeResetPasswordActivity();

    @ContributesAndroidInjector(modules = ForgetPasswordModule.class)
    abstract ForgetPasswordActivity contributeForgetPasswordActivity();

    @ContributesAndroidInjector(modules = CompanyModule.class)
    abstract CompanyMainActivity contributeCompanyMainActivity();

    @ContributesAndroidInjector(modules = CompanyOrdersModule.class)
    abstract CompanyOrdersActivity contributeCompanyOrdersActivity();

    @ContributesAndroidInjector(modules = AddBillModule.class)
    abstract AddBillActivity contributeAddBillActivity();
}
