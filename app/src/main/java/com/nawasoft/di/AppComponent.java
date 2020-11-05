package com.nawasoft.di;

import android.app.Application;

import com.nawasoft.datalayer.cache.CacheModule;
import com.nawasoft.datalayer.database.DatabaseModule;
import com.nawasoft.datalayer.http.ApiServiceModule;
import com.nawasoft.datalayer.sharedpref.SharedPrefModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        FragmentBuilder.class,
        ServiceBuilder.class,
        ApiServiceModule.class,
        SharedPrefModule.class,
        CacheModule.class,
        DatabaseModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();


    }

    void inject(App app);
}
