package com.nawasoft.datalayer.http.companyservice;

import com.google.gson.Gson;
import com.nawasoft.datalayer.http.apimodel.request.AddBillRequest;
import com.nawasoft.datalayer.http.apimodel.request.CheckBarcodeRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyAllTotalsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyCardsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyOrdersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCurrenciesRequest;
import com.nawasoft.datalayer.http.apimodel.response.barcode.CheckBarcodeResponse;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.http.apimodel.response.companycard.GetCompanyCardsResponse;
import com.nawasoft.datalayer.http.apimodel.response.currency.GetCurrenciesResponse;
import com.nawasoft.datalayer.http.apimodel.response.oreders.GetAllTotalsResponse;
import com.nawasoft.datalayer.http.apimodel.response.oreders.company.GetCompanyOrdersResponse;
import com.nawasoft.datalayer.http.retrofit.IRetrofitService;
import com.nawasoft.datalayer.model.Currency;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class ApiCompanyServiceImp implements IApiCompanyService {
    private IRetrofitService retrofitService;

    public ApiCompanyServiceImp(IRetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public Observable<CheckBarcodeResponse> checkBarcode(CheckBarcodeRequest request) {
        return retrofitService.checkBarcode(request);
    }

    @Override
    public Observable<GetCompanyOrdersResponse> getCompanyOrders(GetCompanyOrdersRequest request) {
        return retrofitService.getCompanyOrders(request);
    }

    @Override
    public Observable<GetAllTotalsResponse> getCompanyAllTotals(GetCompanyAllTotalsRequest request) {
        return retrofitService.getCompanyAllTotals(request);
    }

    @Override
    public Observable<List<Currency>> getCurrencies(GetCurrenciesRequest request) {
        return retrofitService.getCurrencies(request)
                .flatMap((Func1<GetCurrenciesResponse, Observable<List<Currency>>>) getCurrenciesResponse -> {
                    if (getCurrenciesResponse.getData().getData() != null)
                        return Observable.just(getCurrenciesResponse.getData().getData());
                    return Observable.empty();
                });
    }

    @Override
    public Observable<GetCompanyCardsResponse> getCompanyCards(GetCompanyCardsRequest request) {
        return retrofitService.getCompanyCards(request);
    }

    @Override
    public Observable<BaseResponse> addBill(AddBillRequest request) {
        System.out.println(new Gson().toJson(request));
        return retrofitService.addBill(request);
    }
}
