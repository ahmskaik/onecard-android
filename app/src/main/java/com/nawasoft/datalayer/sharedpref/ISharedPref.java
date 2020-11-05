package com.nawasoft.datalayer.sharedpref;

import com.nawasoft.datalayer.model.User;

public interface ISharedPref {

    void setLoggedIn();

    void setLoggedOut();

    boolean isLoggedIn();

    void setLanguage(String language);

    String getLanguage();

    void setMemberId(int id);

    int getMemberId();

    void saveUser(User user);

    User getUser();

    void saveCountry(String countryId);

    String getSavedCountry();

    void saveCity(String cityId);

    String getSavedCity();

    void setAccountType(int type);

    int getAccountType();


    void setCompanyId(int id);

    int getCompanyId();

    void setToken(String token);

    String getToken();
}
