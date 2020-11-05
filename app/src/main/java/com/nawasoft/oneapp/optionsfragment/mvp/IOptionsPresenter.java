package com.nawasoft.oneapp.optionsfragment.mvp;

public interface IOptionsPresenter {
    void logout();

    boolean isLoggedIn();

    String getUserName();
}
