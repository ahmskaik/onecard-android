package com.nawasoft.oneapp.common;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.nawasoft.datalayer.model.LatLong;
import com.nawasoft.oneapp.callbacks.OnBranchClickCallback;
import com.nawasoft.oneapp.util.Util;

public class OnBranchClickImpl implements OnBranchClickCallback {
    private Activity activity;

    public OnBranchClickImpl(@NonNull Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onLocationClick(LatLong latLong, String title) {
        Util.openMap(activity, Double.parseDouble(latLong.getLatitude()), Double.parseDouble(latLong.getLongitude()), title);
    }

    @Override
    public void onMobileClick(String mobileNumber) {
        Util.callNumber(activity, mobileNumber);
    }

    @Override
    public void onMessageClick(String email) {
        Util.sendEmail(activity, email);
    }
}
