package com.nawasoft.di;

import com.nawasoft.service.FirebaseCloudMessagingService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceBuilder {

    @ContributesAndroidInjector
    abstract FirebaseCloudMessagingService firebaseService();
}
