package com.nawasoft.oneapp.orders.mvp;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetAllTotalsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetOrdersRequest;
import com.nawasoft.datalayer.http.apimodel.response.oreders.GetAllTotalsResponse;
import com.nawasoft.datalayer.http.apimodel.response.oreders.GetOrdersResponse;
import com.nawasoft.datalayer.model.CurrencyTotal;
import com.nawasoft.datalayer.model.Order;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class OrdersModel implements OrdersMVP.Model {

    private IApiService service;
    private ISharedPref sharedPref;

    public OrdersModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<List<Order>> getOrders() {
        return service.getOrders(
                new GetOrdersRequest(
                        ApiTags.GetOrders.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId())
                )
        ).flatMap((Func1<GetOrdersResponse, Observable<List<Order>>>) getOrdersResponse -> {
            if (getOrdersResponse.getData().getData() == null)
                return Observable.empty();

            String baseLink = getOrdersResponse.getData().getData().getCompanyBasePhotoLink();
            for (Order order: getOrdersResponse.getData().getData().getOrders()) {
                order.setCompanyPhotoLink(baseLink + order.getCompanyPhotoLink());
            }
            return Observable.just(getOrdersResponse.getData().getData().getOrders());
        });
    }

    @Override
    public Observable<List<String>> getTotals() {
        Observable<GetAllTotalsResponse> observable = service.getAllTotals(
                new GetAllTotalsRequest(
                        ApiTags.GetAllTotals.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId())
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
