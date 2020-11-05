package com.nawasoft.datalayer.http.companyservice;

import com.nawasoft.datalayer.http.apimodel.request.AddBillRequest;
import com.nawasoft.datalayer.http.apimodel.request.CheckBarcodeRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyAllTotalsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyCardsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyOrdersRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCurrenciesRequest;
import com.nawasoft.datalayer.http.apimodel.response.barcode.CheckBarcodeResponse;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.http.apimodel.response.companycard.GetCompanyCardsResponse;
import com.nawasoft.datalayer.http.apimodel.response.oreders.GetAllTotalsResponse;
import com.nawasoft.datalayer.http.apimodel.response.oreders.company.GetCompanyOrdersResponse;
import com.nawasoft.datalayer.model.Currency;

import java.util.List;

import rx.Observable;

public interface IApiCompanyService {
    Observable<CheckBarcodeResponse> checkBarcode(CheckBarcodeRequest request);

    Observable<GetCompanyOrdersResponse> getCompanyOrders(GetCompanyOrdersRequest request);

    Observable<GetAllTotalsResponse> getCompanyAllTotals(GetCompanyAllTotalsRequest request);

    Observable<List<Currency>> getCurrencies(GetCurrenciesRequest request);

    Observable<GetCompanyCardsResponse> getCompanyCards(GetCompanyCardsRequest request);

    Observable<BaseResponse> addBill(AddBillRequest request);
}
