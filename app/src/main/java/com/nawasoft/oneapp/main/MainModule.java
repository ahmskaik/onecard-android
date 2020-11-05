package com.nawasoft.oneapp.main;

import com.nawasoft.datalayer.sharedpref.ISharedPref;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    IMainPresenter provideMainPresenter(ISharedPref sharedPref) {
        return new MainPresenter(sharedPref);
    }
}
