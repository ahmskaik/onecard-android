package com.nawasoft.oneapp.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nawasoft.base.BaseActivity;
import com.nawasoft.oneapp.FRAGMENTS;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnBackPressedCallback;
import com.nawasoft.oneapp.companiesfragment.CompaniesFragment;
import com.nawasoft.companyapp.main.CompanyMainActivity;
import com.nawasoft.datalayer.model.AccountType;
import com.nawasoft.oneapp.favouritefragment.FavouriteFragment;
import com.nawasoft.oneapp.homefragment.HomeFragment;
import com.nawasoft.oneapp.offers.OffersFragment;
import com.nawasoft.oneapp.optionsfragment.OptionsFragment;
import com.nawasoft.oneapp.util.Util;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;


public class MainActivity extends BaseActivity implements OnBackPressedCallback {

    private static final String OFFER_ID = "offer_id";

    @Inject
    IMainPresenter presenter;

    private FRAGMENTS currentFragment = FRAGMENTS.HomeFragment;

    private boolean canExit = false;

    private String offerId = "";

    public static Intent getOpenOfferIntent(Context context, String offerId) {
        Bundle bundle = new Bundle();
        bundle.putString(OFFER_ID, offerId);
        return new Intent(context, MainActivity.class).putExtras(bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Util.changeAppLanguage(this, presenter.getLanguage());

        if (presenter.getAccountType() == AccountType.COMPANY) {
            Util.goToActivity(this, CompanyMainActivity.class, null);
            finish();
        } else {
            findViewById(R.id.layout).requestFocus();
            getFireBaseId();
            if (getIntent().getExtras() != null)
                offerId = getIntent().getExtras().getString(OFFER_ID, "");
            showFragment(HomeFragment.getInstance(this, offerId), "");
        }
    }

    private void getFireBaseId() {
        new Thread() {
            @Override
            public void run() {
                FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(task -> {
                            System.out.println("FirebaseToken: " + task.getResult().getToken());
                            System.out.println("FirebaseId: " + task.getResult().getId());
                            presenter.saveToken(task.getResult().getToken());
                        });

            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        }

        if (currentFragment != FRAGMENTS.HomeFragment) {
            replaceFragment(new HomeFragment(this), FRAGMENTS.HomeFragment.toString());
            return;
        }

        if (canExit) {
            super.onBackPressed();
            return;
        }

        Toast.makeText(this, getString(R.string.exit_app_message), Toast.LENGTH_SHORT).show();
        canExit = true;

        new Handler().postDelayed(() -> canExit = false, 2000);
    }

    @OnClick({R.id.home_button, R.id.options_button, R.id.companies_button, R.id.offers_button, R.id.favourite_button})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_button :
                replaceFragment(new HomeFragment(this), FRAGMENTS.HomeFragment.toString());
                break;
            case R.id.options_button :
                replaceFragment(new OptionsFragment(this), FRAGMENTS.OptionsFragment.toString());
                break;
            case R.id.companies_button :
                replaceFragment(new CompaniesFragment(this), FRAGMENTS.CompaniesFragment.toString());
                break;
            case R.id.offers_button :
                replaceFragment(new OffersFragment(this), FRAGMENTS.OffersFragment.toString());
                break;
            case R.id.favourite_button :
                goToFavouritesList();
                break;
        }
    }

    private void goToFavouritesList() {
        if (!presenter.isLoggedIn()) {
            showAlert(getString(R.string.must_login_message));
            return;
        }
        replaceFragment(new FavouriteFragment(this), FRAGMENTS.FavouriteFragment.toString());
    }


    private void replaceFragment(Fragment fragment, String tag) {
        Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment1.getTag() != null && fragment1.getTag().equalsIgnoreCase(tag)) {
            return;
        }

        currentFragment = FRAGMENTS.valueOf(tag);

        showFragment(fragment, tag);
    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    private void showFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }
}
