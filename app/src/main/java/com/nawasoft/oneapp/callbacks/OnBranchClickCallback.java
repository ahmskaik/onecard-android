package com.nawasoft.oneapp.callbacks;

import com.nawasoft.datalayer.model.LatLong;

public interface OnBranchClickCallback {
    void onLocationClick(LatLong latLong, String title);
    void onMobileClick(String mobileNumber);
    void onMessageClick(String email);
}
