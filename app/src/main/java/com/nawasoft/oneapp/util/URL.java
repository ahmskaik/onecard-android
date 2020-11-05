package com.nawasoft.oneapp.util;

import androidx.annotation.NonNull;

public enum URL {
    PRIVACY_POLICY("http://onecard.global/privacy-policy"),
    CONTACT_US("http://onecard.global/contact-us-app"),
    ABOUT_US("http://onecard.global/about-us-app"),
    DISTRIBUTORS("http://onecard.global/distributors-app"),
    BASE_OFFER("http://onecard.global/offer/");


    private String url;

    URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @NonNull
    @Override
    public String toString() {
        return getUrl();
    }
}
