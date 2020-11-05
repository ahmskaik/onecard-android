package com.nawasoft.datalayer.http.retrofit;

import com.nawasoft.datalayer.http.apimodel.request.ActiveCardRequest;
import com.nawasoft.datalayer.http.apimodel.request.AddBillRequest;
import com.nawasoft.datalayer.http.apimodel.request.AddOfferToFavouritesRequest;
import com.nawasoft.datalayer.http.apimodel.request.CheckBarcodeRequest;
import com.nawasoft.datalayer.http.apimodel.request.EditProfileRequest;
import com.nawasoft.datalayer.http.apimodel.request.ForgotPasswordRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetAllCompaniesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetAllCountriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetAllTotalsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCategoriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCitiesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyAllTotalsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyCardsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyDetailsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyOrdersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCountriesRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCurrenciesRequest;
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
import com.nawasoft.datalayer.http.apimodel.request.RegisterRequest;
import com.nawasoft.datalayer.http.apimodel.request.ResetPasswordRequest;
import com.nawasoft.datalayer.http.apimodel.request.RefreshTokenRequest;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;
import com.nawasoft.datalayer.http.apimodel.response.activecard.ActiveCardResponse;
import com.nawasoft.datalayer.http.apimodel.response.barcode.CheckBarcodeResponse;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.http.apimodel.response.categories.GetCategoriesResponse;
import com.nawasoft.datalayer.http.apimodel.response.cities.GetCitiesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companies.GetAllCompaniesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companies.GetFeaturedCompaniesResponse;
import com.nawasoft.datalayer.http.apimodel.response.companycard.GetCompanyCardsResponse;
import com.nawasoft.datalayer.http.apimodel.response.companydetails.GetCompanyDetailsResponse;
import com.nawasoft.datalayer.http.apimodel.response.countries.GetCountriesResponse;
import com.nawasoft.datalayer.http.apimodel.response.currency.GetCurrenciesResponse;
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
import com.nawasoft.datalayer.http.apimodel.response.oreders.company.GetCompanyOrdersResponse;
import com.nawasoft.datalayer.http.apimodel.response.profile.ResetPasswordResponse;
import com.nawasoft.datalayer.http.apimodel.response.rate.MakeRateResponse;
import com.nawasoft.datalayer.http.apimodel.response.register.RegisterResponse;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetAllTicketsResponse;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetMyTicketsResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface IRetrofitService {
    //String ENDPOINT = "api";
    String ENDPOINT = "Apitest";

    @POST(ENDPOINT)
    Observable<LoginResponse> login(@Body LoginRequest request);

    @POST(ENDPOINT)
    Observable<RegisterResponse> register(@Body RegisterRequest request);

    @POST(ENDPOINT)
    Observable<GetHomeSliderResponse> getHomeSlide(@Body GetHomeSliderRequest request);

    @POST(ENDPOINT)
    Observable<GetCategoriesResponse> getCategories(@Body GetCategoriesRequest request);

    @POST(ENDPOINT)
    Observable<GetFeaturedOffersResponse> getFeaturedOffers(@Body GetFeaturedOffersRequest request);

    @POST(ENDPOINT)
    Observable<GetFeaturedCompaniesResponse> getFeaturedCompanies(@Body GetFeaturedCompaniesRequest request);

    @POST(ENDPOINT)
    Observable<GetAllCompaniesResponse> getAllCompanies(@Body GetAllCompaniesRequest request);

    @POST(ENDPOINT)
    Observable<GetOffersResponse> getOffers(@Body GetOffersRequest request);

    @POST(ENDPOINT)
    Observable<GetCompanyDetailsResponse> getCompanyDetails(@Body GetCompanyDetailsRequest request);

    @POST(ENDPOINT)
    Observable<GetOfferDetailsResponse> getOfferDetails(@Body GetOfferDetailsRequest request);

    @POST(ENDPOINT)
    Observable<GetIsOfferFavouriteResponse> getIsOfferFavourite(@Body GetIsOfferFavouriteRequest request);

    @POST(ENDPOINT)
    Observable<BaseResponse> addOfferToFavourites(@Body AddOfferToFavouritesRequest request);

    @POST(ENDPOINT)
    Observable<GetFavouriteOffersResponse> getFavouriteOffers(@Body GetFavouriteOffersRequest request);

    @POST(ENDPOINT)
    Observable<GetNearestOfferResponse> getNearestOffers(@Body GetNearestOffersRequest request);

    @POST(ENDPOINT)
    Observable<GetCountriesResponse> getCountries(@Body GetCountriesRequest request);

    @POST(ENDPOINT)
    Observable<GetCountriesResponse> getAllCountries(@Body GetAllCountriesRequest request);

    @POST(ENDPOINT)
    Observable<GetCitiesResponse> getCities(@Body GetCitiesRequest request);

    @POST(ENDPOINT)
    Observable<ResetPasswordResponse> resetPassword(@Body ResetPasswordRequest resetPasswordRequest);

    @POST(ENDPOINT)
    Observable<ForgotPasswordResponse> forgetPassword(@Body ForgotPasswordRequest request);

    @POST(ENDPOINT)
    Observable<EditProfileResponse> editProfile(@Body EditProfileRequest request);

    @POST(ENDPOINT)
    Observable<GetOrdersResponse> getOrders(@Body GetOrdersRequest request);

    @POST(ENDPOINT)
    Observable<GetAllTotalsResponse> getAllTotals(@Body GetAllTotalsRequest request);

    @POST(ENDPOINT)
    io.reactivex.Observable<GetMyTicketsResponse> getMyTickets(@Body GetMyTicketsRequest request);

    @POST(ENDPOINT)
    Observable<GetAllTicketsResponse> getAllTickets(@Body BaseRequest request);

    // company
    @POST(ENDPOINT)
    Observable<CheckBarcodeResponse > checkBarcode(@Body CheckBarcodeRequest request);

    @POST(ENDPOINT)
    Observable<GetCompanyOrdersResponse> getCompanyOrders(@Body GetCompanyOrdersRequest request);

    @POST(ENDPOINT)
    Observable<GetAllTotalsResponse> getCompanyAllTotals(@Body GetCompanyAllTotalsRequest request);

    @POST(ENDPOINT)
    Observable<GetCompanyCardsResponse> getCompanyCards(@Body GetCompanyCardsRequest request);

    @POST(ENDPOINT)
    Observable<GetCurrenciesResponse> getCurrencies(@Body GetCurrenciesRequest request);

    @POST(ENDPOINT)
    Observable<BaseResponse> addBill(@Body AddBillRequest request);

    @POST(ENDPOINT)
    Observable<ActiveCardResponse> activeCard(@Body ActiveCardRequest request);

    @POST(ENDPOINT)
    Observable<MakeRateResponse> makeRate(@Body MakeRateRequest request);

    @POST(ENDPOINT)
    Observable<BaseResponse> refreshToken(@Body RefreshTokenRequest request);
}

