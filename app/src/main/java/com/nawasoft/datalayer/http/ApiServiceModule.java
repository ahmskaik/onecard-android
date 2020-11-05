package com.nawasoft.datalayer.http;

import com.nawasoft.datalayer.http.companyservice.ApiCompanyServiceImp;
import com.nawasoft.datalayer.http.companyservice.IApiCompanyService;
import com.nawasoft.datalayer.http.retrofit.IRetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiServiceModule {

    private final String BASE_URL = "http://onecard.global/";

    @Provides
    OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideClient())
                .build();
    }

    @Provides
    @Singleton
    IRetrofitService provideRetrofitService() {
        return provideRetrofit(BASE_URL).create(IRetrofitService.class);
    }

    @Provides
    @Singleton
    IApiService provideApiService(IRetrofitService retrofitService) {
        return new ApiServiceImp(retrofitService);
    }

    @Provides
    @Singleton
    IApiCompanyService provideApiCompanyService(IRetrofitService retrofitService) {
        return new ApiCompanyServiceImp(retrofitService);
    }
}
