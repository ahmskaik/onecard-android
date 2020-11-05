package com.nawasoft.di;

import com.nawasoft.oneapp.optionsfragment.mvp.OptionsModule;
import com.nawasoft.oneapp.payment.activecard.ActiveCardDialog;
import com.nawasoft.oneapp.payment.activecard.mvp.ActiveCardModule;
import com.nawasoft.oneapp.companiesfragment.CompaniesFragment;
import com.nawasoft.oneapp.companiesfragment.mvp.CompaniesModule;
import com.nawasoft.oneapp.companyfragment.CompanyFragment;
import com.nawasoft.oneapp.companyfragment.mvp.CompanyModule;
import com.nawasoft.oneapp.editprofile.EditProfileFragment;
import com.nawasoft.oneapp.editprofile.mvp.EditProfileModule;
import com.nawasoft.oneapp.favouritefragment.FavouriteFragment;
import com.nawasoft.oneapp.favouritefragment.mvp.FavouriteModule;
import com.nawasoft.oneapp.homefragment.HomeFragment;
import com.nawasoft.oneapp.homefragment.mvp.HomeModule;
import com.nawasoft.oneapp.language.LanguageDialog;
import com.nawasoft.oneapp.offerfragmnet.OfferFragment;
import com.nawasoft.oneapp.offerfragmnet.mvp.OfferModule;
import com.nawasoft.oneapp.offers.filter.FilterDialog;
import com.nawasoft.oneapp.offers.filter.mvp.FilterModule;
import com.nawasoft.oneapp.offers.mapfragment.OfferMapFragment;
import com.nawasoft.oneapp.offers.mapfragment.mvp.MapModule;
import com.nawasoft.oneapp.offers.mvp.OffersModule;
import com.nawasoft.oneapp.offers.OffersFragment;
import com.nawasoft.oneapp.optionsfragment.OptionsFragment;
import com.nawasoft.oneapp.orders.OrdersFragment;
import com.nawasoft.oneapp.orders.mvp.OrdersModule;
import com.nawasoft.oneapp.rate.RateDialog;
import com.nawasoft.oneapp.rate.mvp.RateModule;
import com.nawasoft.oneapp.tickets.TicketsFragment;
import com.nawasoft.oneapp.tickets.mvp.TicketsModule;
import com.nawasoft.oneapp.tickets.mytickets.MyTicketsFragment;
import com.nawasoft.oneapp.tickets.mytickets.mvp.MyTicketsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = OptionsModule.class)
    abstract OptionsFragment contributeOptionsFragment();

    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector(modules = CompaniesModule.class)
    abstract CompaniesFragment contributeCompaniesFragment();

    @ContributesAndroidInjector(modules = OffersModule.class)
    abstract OffersFragment contributeOffersFragment();

    @ContributesAndroidInjector(modules = CompanyModule.class)
    abstract CompanyFragment contributeCompanyFragment();

    @ContributesAndroidInjector(modules = OfferModule.class)
    abstract OfferFragment contributeOfferFragment();

    @ContributesAndroidInjector(modules = FavouriteModule.class)
    abstract FavouriteFragment contributeFavouriteFragment();

    @ContributesAndroidInjector(modules = MapModule.class)
    abstract OfferMapFragment contributeOfferMapFragment();

    @ContributesAndroidInjector(modules = EditProfileModule.class)
    abstract EditProfileFragment contribureEditProfileFragment();

    @ContributesAndroidInjector(modules = OrdersModule.class)
    abstract OrdersFragment contributeOrdersFragment();

    @ContributesAndroidInjector(modules = FilterModule.class)
    abstract FilterDialog contributeFilterDialog();

    @ContributesAndroidInjector(modules = TicketsModule.class)
    abstract TicketsFragment contributeTicketsFragment();

    @ContributesAndroidInjector(modules = MyTicketsModule.class)
    abstract MyTicketsFragment contributeMyTicketsFragment();

    @ContributesAndroidInjector
    abstract LanguageDialog contributeLanguageDialog();

    @ContributesAndroidInjector(modules = ActiveCardModule.class)
    abstract ActiveCardDialog contributeActiveCardDialog();

    @ContributesAndroidInjector(modules = RateModule.class)
    abstract RateDialog contributeRateDialog();
}
