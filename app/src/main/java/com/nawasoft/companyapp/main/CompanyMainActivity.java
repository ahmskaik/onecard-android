package com.nawasoft.companyapp.main;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.nawasoft.base.BaseActivity;
import com.nawasoft.companyapp.addbill.AddBillActivity;
import com.nawasoft.companyapp.main.mvp.CompanyMainMVP;
import com.nawasoft.companyapp.main.qrcode.QrCodeCallBack;
import com.nawasoft.companyapp.main.qrcode.QrCodeFragment;
import com.nawasoft.companyapp.reports.CompanyOrdersActivity;
import com.nawasoft.oneapp.main.MainActivity;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class CompanyMainActivity extends BaseActivity implements CompanyMainMVP.View, QrCodeCallBack {

    @Inject
    CompanyMainMVP.Presenter presenter;

    @BindView(R.id.back_button)
    View backButton;
    @BindView(R.id.fragment_container)
    FrameLayout qrCodeContainer;
    @BindView(R.id.qr_code_button)
    View qrCodeButton;
    @BindView(R.id.reports_button)
    View reportsButton;
    @BindView(R.id.logout_button)
    View logoutButton;

    private final int CAMERA_REQUEST_CODE = 412;
    private String[] permissions = {Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_main);
        setProgressBar(R.id.company_main_progressBar);
        ButterKnife.bind(this);


        backButton.setOnClickListener(view -> onBackPressed());
        qrCodeButton.setOnClickListener(view -> showQrCode());
        logoutButton.setOnClickListener(view -> {
            presenter.logout();
            Util.goToActivity(this, MainActivity.class, null);
            finish();
        });
        reportsButton.setOnClickListener(view ->
                Util.goToActivity(this, CompanyOrdersActivity.class, null));
        presenter.setView(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            cancelQr();
        else
            super.onBackPressed();
    }

    // QrCode Methods
    @AfterPermissionGranted(CAMERA_REQUEST_CODE)
    private void showQrCode() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            logoutButton.setVisibility(View.GONE);
            qrCodeContainer.setVisibility(View.VISIBLE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new QrCodeFragment())
                    .addToBackStack("qr_code")
                    .commit();
        } else {
            EasyPermissions.requestPermissions(
                    this, "Camera Permission", CAMERA_REQUEST_CODE, permissions
            );
        }

    }

    @Override
    public void onResult(String result) {
        presenter.checkQrCode(result);
    }

    @Override
    public void cancelQr() {
        logoutButton.setVisibility(View.VISIBLE);
        getSupportFragmentManager().popBackStack();
        qrCodeContainer.setVisibility(View.GONE);
    }

    // view methods


    @Override
    public void setCorrectQrCode(String memberId) {
        Bundle bundle = new Bundle();
        bundle.putString(AddBillActivity.MEMBER_ID, memberId);
        Util.goToActivity(this, AddBillActivity.class, bundle);
    }
}
