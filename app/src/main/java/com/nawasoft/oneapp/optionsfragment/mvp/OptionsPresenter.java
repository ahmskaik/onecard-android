package com.nawasoft.oneapp.optionsfragment.mvp;

import com.nawasoft.datalayer.database.IDatabaseService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

public class OptionsPresenter implements IOptionsPresenter {

    private ISharedPref sharedPref;
    private IDatabaseService database;

    public OptionsPresenter(ISharedPref sharedPref, IDatabaseService database) {
        this.sharedPref = sharedPref;
        this.database = database;
    }

    @Override
    public void logout() {
        sharedPref.setLoggedOut();
        database.deleteAllTickets();
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPref.isLoggedIn();
    }

    @Override
    public String getUserName() {
        return sharedPref.getUser().getFullName();
    }
}
