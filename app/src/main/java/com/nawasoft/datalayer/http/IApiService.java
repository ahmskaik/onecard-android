package com.nawasoft.datalayer.http;

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
import com.nawasoft.datalayer.http.apimodel.response.companies.GetAllCompaniesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companies.GetFeaturedCompaniesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companydetails.GetCompanyDetailsResponse;
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
import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.Country;

import java.util.List;

import rx.Observable;

public interface IApiService {

    Observable<LoginResponse> login(LoginRequest request);


    Observable<RegisterResponse> register(RegisterRequest request);

    Observable<GetHomeSliderResponse> getHomeSlider(GetHomeSliderRequest request);

    Observable<GetCategoriesResponse> getCategories(GetCategoriesRequest request);

    Observable<GetFeaturedOffersResponse> getFeaturedOffers(GetFeaturedOffersRequest request);

    Observable<GetFeaturedCompaniesResponse> getFeaturedCompanies(GetFeaturedCompaniesRequest request);

    Observable<GetAllCompaniesResponse> getAllCompanies(GetAllCompaniesRequest request);

    Observable<GetOffersResponse> getOffers(GetOffersRequest request);

    Observable<GetCompanyDetailsResponse> getCompanyDetails(GetCompanyDetailsRequest request);


    Observable<GetOfferDetailsResponse> getOfferDetails(GetOfferDetailsRequest request);

    Observable<GetIsOfferFavouriteResponse> getIsOfferFavourite(GetIsOfferFavouriteRequest request);

    Observable<BaseResponse> addOfferToFavourite(AddOfferToFavouritesRequest request);

    Observable<GetFavouriteOffersResponse> getFavouriteOffers(GetFavouriteOffersRequest request);

    Observable<GetNearestOfferResponse> getNearestOffers(GetNearestOffersRequest request);

    Observable<List<Country>> getCountries(GetCountriesRequest request);

    Observable<List<City>> getCities(GetCitiesRequest request);

    Observable<List<Country>> getAllCountries(GetAllCountriesRequest request);

    Observable<ResetPasswordResponse> resetPassword(ResetPasswordRequest request);

    Observable<ForgotPasswordResponse> forgetPassword(ForgotPasswordRequest request);

    Observable<EditProfileResponse> editProfile(EditProfileRequest request);

    Observable<GetOrdersResponse> getOrders(GetOrdersRequest request);

    Observable<GetAllTotalsResponse> getAllTotals(GetAllTotalsRequest request);

    io.reactivex.Observable<GetMyTicketsResponse> getMyTickets(GetMyTicketsRequest request);

    Observable<GetAllTicketsResponse> getAllTickets(BaseRequest request);

    Observable<ActiveCardResponse> activeCard(ActiveCardRequest request);

    Observable<MakeRateResponse> makeRate(MakeRateRequest request);

    Observable<BaseResponse> refreshToken(RefreshTokenRequest request);
}

