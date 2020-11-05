package com.nawasoft.oneapp.optionsfragment.mvp;

import com.nawasoft.datalayer.database.IDatabaseService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class OptionsModule {

    @Provides
    IOptionsPresenter providePresenter(ISharedPref sharedPref, IDatabaseService database) {
        return new OptionsPresenter(sharedPref, database);
    }
}

