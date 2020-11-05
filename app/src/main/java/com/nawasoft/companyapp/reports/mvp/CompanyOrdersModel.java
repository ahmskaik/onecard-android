package com.nawasoft.companyapp.reports.mvp;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyAllTotalsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyOrdersRequest;
import com.nawasoft.datalayer.http.apimodel.response.oreders.GetAllTotalsResponse;
import com.nawasoft.datalayer.http.apimodel.response.oreders.company.GetCompanyOrdersData;
import com.nawasoft.datalayer.http.apimodel.response.oreders.company.GetCompanyOrdersResponse;
import com.nawasoft.datalayer.http.companyservice.IApiCompanyService;
import com.nawasoft.datalayer.model.CompanyOrder;
import com.nawasoft.datalayer.model.CurrencyTotal;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class CompanyOrdersModel implements CompanyOrdersMVP.Model {

    private IApiCompanyService service;
    private ISharedPref sharedPref;

    public CompanyOrdersModel(IApiCompanyService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<List<CompanyOrder>> getCompanyOrders() {
        return service.getCompanyOrders(
                new GetCompanyOrdersRequest(
                        ApiTags.GetCompanyOrders.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getCompanyId())
                )
        ).flatMap((Func1<GetCompanyOrdersResponse, Observable<List<CompanyOrder>>>) getCompanyOrdersResponse -> {
            GetCompanyOrdersData data = getCompanyOrdersResponse.getData().getData();
            if (data == null)
                return Observable.empty();

            String baseLink = data.getCompanyBasePhotoLink();
            for (CompanyOrder companyOrder: data.getCompanyOrders()) {
                companyOrder.setCompanyPhotoLink(baseLink + companyOrder.getCompanyPhotoLink());
            }

            return Observable.just(data.getCompanyOrders());
        });
    }

    @Override
    public Observable<List<String>> getAllTotals() {
        Observable<GetAllTotalsResponse> observable = service.getCompanyAllTotals(
                new GetCompanyAllTotalsRequest(
                        ApiTags.GetAllTotals.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getCompanyId())
                )
        );

        return observable.flatMap((Func1<GetAllTotalsResponse, Observable<List<String>>>) getAllTotalsResponse -> {
            List<CurrencyTotal> currencyTotals = getAllTotalsResponse.getData().getData();
            if (currencyTotals == null)
                return Observable.empty();

            List<String> totals = new ArrayList<>();
            for (CurrencyTotal currencyTotal : currencyTotals) {
                totals.add(currencyTotal.getTotalValue());
            }
            return Observable.just(totals);
        });
    }
}
