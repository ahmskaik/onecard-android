package com.nawasoft.oneapp.main;

import com.nawasoft.datalayer.model.AccountType;

public interface IMainPresenter {
    AccountType getAccountType();

    boolean isLoggedIn();

    void saveToken(String token);

    String getLanguage();
}
