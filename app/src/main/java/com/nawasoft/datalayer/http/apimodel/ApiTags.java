package com.nawasoft.datalayer.http.apimodel;

public enum ApiTags {
    Login("login"),
    Register("register"),
    GetHomeSlider("getHomeSlider"),
    GetCategories("getCategories"),
    GetFeaturedOffers("getFeaturedOffers"),
    GetFeaturedCompanies("getFeaturedCompanies"),
    GetAllCompanies("getAllCompanies"),
    GetCategoryCards("getCategoryCards"),
    GetOffers("getOffers"),
    GetCompanyDetails("getCompanyDetails"),
    GetOfferDetails("getCardDetails"),
    GetIsOfferFavourite("getIfFavourite"),
    AddOfferToFavourites("addToFavourites"),
    GetFavouriteOffers("getFavourites"),
    GetNearestCompanies("getNearestCompanies"),
    GetCountries("getCountries"),
    GetCities("getCities"),
    GetAllCountries("getAllCountries"),
    ResetPassword("changePassword"),
    ForgotPassword("forgotPassword"),
    EditProfile("editProfile"),
    GetOrders("getMemberOrders"),
    GetAllTotals("getEachCurrencyTotal"),
    GetAllTickets("getAllTickets"),
    GetMyTickets("getMyTickets"),
    CheckBarcodeInfo("checkBarcodeInfo"),
    GetCompanyOrders("getCompanyOrders"),
    GetCurrencies("getCurrency"),
    GetCompanyCards("getCompanyCards"),
    AddBill("addAnOrder"),
    ActiveCard("registerNewQr"),
    MakeRate("addEditRateComment"),
    RefreshToken("updateToken");

    private String value;

    ApiTags(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
