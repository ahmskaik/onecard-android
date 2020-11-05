package com.nawasoft.oneapp.main;

import com.nawasoft.datalayer.model.AccountType;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

public class MainPresenter implements IMainPresenter {

    private ISharedPref sharedPref;

    public MainPresenter(ISharedPref sharedPref) {
        this.sharedPref = sharedPref;
    }

    @Override
    public AccountType getAccountType() {
        if (sharedPref.isLoggedIn()) {
            return AccountType.getTypeByValue(sharedPref.getAccountType());
        }
        return AccountType.MEMBER;
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPref.isLoggedIn();
    }

    @Override
    public void saveToken(String token) {
        sharedPref.setToken(token);
    }

    @Override
    public String getLanguage() {
        return sharedPref.getLanguage();
    }
}
