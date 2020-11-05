package com.nawasoft.datalayer.cache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {
    @Singleton
    @Provides
    ICacheRepository provideCacheRepository() {
        return new CacheRepositoryImp();
    }
}
