package com.nawasoft.datalayer.sharedpref;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {

    @Provides
    @Singleton
    ISharedPref provideSharedPref(Context context) {
        return new SharedPrefImp(context);
    }
}
