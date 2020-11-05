package com.nawasoft.oneapp.rate.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.MakeRateRequest;
import com.nawasoft.datalayer.http.apimodel.response.rate.MakeRateResponse;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;

public class RateModel implements RateMVP.Model {
    private IApiService service;
    private ISharedPref sharedPref;


    public RateModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }


    @Override
    public Observable<MakeRateResponse> makeRate(String offerId, int rate, String comment) {
        return service.makeRate(
                new MakeRateRequest(
                        ApiTags.MakeRate.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId()),
                        offerId,
                        String.valueOf(rate),
                        comment
                )
        );
    }
}
