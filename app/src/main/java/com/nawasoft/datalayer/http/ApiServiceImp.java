package com.nawasoft.datalayer.http;

import com.google.gson.Gson;
import com.nawasoft.datalayer.http.apimodel.request.ActiveCardRequest;
import com.nawasoft.datalayer.http.apimodel.request.AddOfferToFavouritesRequest;
import com.nawasoft.datalayer.http.apimodel.request.EditProfileRequest;
import com.nawasoft.datalayer.http.apimodel.request.ForgotPasswordRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetAllCompaniesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetAllCountriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetAllTotalsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCategoriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCitiesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyDetailsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCountriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetFavouriteOffersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetFeaturedCompaniesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetFeaturedOffersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetHomeSliderRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetIsOfferFavouriteRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetNearestOffersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetOfferDetailsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetOffersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetOrdersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetMyTicketsRequest;
import com.nawasoft.datalayer.http.apimodel.request.LoginRequest;
import com.nawasoft.datalayer.http.apimodel.request.MakeRateRequest;
import com.nawasoft.datalayer.http.apimodel.request.RefreshTokenRequest;
import com.nawasoft.datalayer.http.apimodel.request.RegisterRequest;
import com.nawasoft.datalayer.http.apimodel.request.ResetPasswordRequest;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;
import com.nawasoft.datalayer.http.apimodel.response.activecard.ActiveCardResponse;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.http.apimodel.response.categories.GetCategoriesResponse;
import com.nawasoft.datalayer.http.apimodel.response.cities.GetCitiesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companies.GetAllCompaniesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companies.GetFeaturedCompaniesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companydetails.GetCompanyDetailsResponse;
import com.nawasoft.datalayer.http.apimodel.response.countries.GetCountriesResponse;
import com.nawasoft.datalayer.http.apimodel.response.editprofile.EditProfileResponse;
import com.nawasoft.datalayer.http.apimodel.response.homeslider.GetHomeSliderResponse;
import com.nawasoft.datalayer.http.apimodel.response.login.ForgotPasswordResponse;
import com.nawasoft.datalayer.http.apimodel.response.login.LoginResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetFeaturedOffersResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOfferDetailsResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOffersResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.favouriteoffers.GetFavouriteOffersResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.favouriteoffers.GetIsOfferFavouriteResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.mapoffers.GetNearestOfferResponse;
import com.nawasoft.datalayer.http.apimodel.response.oreders.GetAllTotalsResponse;
import com.nawasoft.datalayer.http.apimodel.response.oreders.GetOrdersResponse;
import com.nawasoft.datalayer.http.apimodel.response.profile.ResetPasswordResponse;
import com.nawasoft.datalayer.http.apimodel.response.rate.MakeRateResponse;
import com.nawasoft.datalayer.http.apimodel.response.register.RegisterResponse;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetAllTicketsResponse;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetMyTicketsResponse;
import com.nawasoft.datalayer.http.retrofit.IRetrofitService;
import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.Country;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class ApiServiceImp implements IApiService {

    private IRetrofitService retrofitService;

    public ApiServiceImp(IRetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public Observable<LoginResponse> login(LoginRequest request) {
        return retrofitService.login(request);
    }

    @Override
    public Observable<RegisterResponse> register(RegisterRequest request) {
        return retrofitService.register(request);
    }

    @Override
    public Observable<GetHomeSliderResponse> getHomeSlider(GetHomeSliderRequest request) {
        return retrofitService.getHomeSlide(request);
    }

    @Override
    public Observable<GetCategoriesResponse> getCategories(GetCategoriesRequest request) {
        return retrofitService.getCategories(request);
    }

    @Override
    public Observable<GetFeaturedOffersResponse> getFeaturedOffers(GetFeaturedOffersRequest request) {
        return retrofitService.getFeaturedOffers(request);
    }

    @Override
    public Observable<GetFeaturedCompaniesResponse> getFeaturedCompanies(GetFeaturedCompaniesRequest request) {
        return retrofitService.getFeaturedCompanies(request);
    }

    @Override
    public Observable<GetAllCompaniesResponse> getAllCompanies(GetAllCompaniesRequest request) {
        return retrofitService.getAllCompanies(request);
    }

    @Override
    public Observable<GetOffersResponse> getOffers(GetOffersRequest request) {
        return retrofitService.getOffers(request);
    }

    @Override
    public Observable<GetCompanyDetailsResponse> getCompanyDetails(GetCompanyDetailsRequest request) {
        return retrofitService.getCompanyDetails(request);
    }

    @Override
    public Observable<GetOfferDetailsResponse> getOfferDetails(GetOfferDetailsRequest request) {
        return retrofitService.getOfferDetails(request);
    }

    @Override
    public Observable<GetIsOfferFavouriteResponse> getIsOfferFavourite(GetIsOfferFavouriteRequest request) {
        System.out.println(new Gson().toJson(request));
        return retrofitService.getIsOfferFavourite(request);
    }

    @Override
    public Observable<BaseResponse> addOfferToFavourite(AddOfferToFavouritesRequest request) {
        return retrofitService.addOfferToFavourites(request);
    }

    @Override
    public Observable<GetFavouriteOffersResponse> getFavouriteOffers(GetFavouriteOffersRequest request) {
        return retrofitService.getFavouriteOffers(request);
    }

    @Override
    public Observable<GetNearestOfferResponse> getNearestOffers(GetNearestOffersRequest request) {
        return retrofitService.getNearestOffers(request);
    }

    @Override
    public Observable<List<Country>> getCountries(GetCountriesRequest request) {
        return retrofitService.getCountries(request)
                .flatMap((Func1<GetCountriesResponse, Observable<List<Country>>>) getCountriesResponse ->
                        Observable.just(getCountriesResponse.getData().getData().getCountries()));
    }

    @Override
    public Observable<List<Country>> getAllCountries(GetAllCountriesRequest request) {
        return retrofitService.getAllCountries(request)
                .flatMap((Func1<GetCountriesResponse, Observable<List<Country>>>) getCountriesResponse ->
                        Observable.just(getCountriesResponse.getData().getData().getCountries()));
    }

    @Override
    public Observable<List<City>> getCities(GetCitiesRequest request) {
        return retrofitService.getCities(request)
                .flatMap((Func1<GetCitiesResponse, Observable<List<City>>>) getCitiesResponse ->
                        Observable.just(getCitiesResponse.getData().getData()));
    }

    @Override
    public Observable<ResetPasswordResponse> resetPassword(ResetPasswordRequest request) {
        return retrofitService.resetPassword(request);
    }

    @Override
    public Observable<ForgotPasswordResponse> forgetPassword(ForgotPasswordRequest request) {
        return retrofitService.forgetPassword(request);
    }

    @Override
    public Observable<EditProfileResponse> editProfile(EditProfileRequest request) {
        return retrofitService.editProfile(request);
    }

    @Override
    public Observable<GetOrdersResponse> getOrders(GetOrdersRequest request) {
        return retrofitService.getOrders(request);
    }

    @Override
    public Observable<GetAllTotalsResponse> getAllTotals(GetAllTotalsRequest request) {
        return retrofitService.getAllTotals(request);
    }

    @Override
    public io.reactivex.Observable<GetMyTicketsResponse> getMyTickets(GetMyTicketsRequest request) {
        return retrofitService.getMyTickets(request);
    }

    @Override
    public Observable<GetAllTicketsResponse> getAllTickets(BaseRequest request) {
        return retrofitService.getAllTickets(request);
    }

    @Override
    public Observable<ActiveCardResponse> activeCard(ActiveCardRequest request) {
        return retrofitService.activeCard(request);
    }

    @Override
    public Observable<MakeRateResponse> makeRate(MakeRateRequest request) {
        return retrofitService.makeRate(request);
    }

    @Override
    public Observable<BaseResponse> refreshToken(RefreshTokenRequest request) {
        return retrofitService.refreshToken(request).doOnNext(baseResponse -> {
            if (baseResponse.getMeta().getStatusNumber() == 200) {
                System.out.println("refreshed");
            } else {
                System.out.println("error in refresh");
            }
        });
    }
}
