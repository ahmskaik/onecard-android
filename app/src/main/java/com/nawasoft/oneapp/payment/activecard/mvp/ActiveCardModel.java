package com.nawasoft.oneapp.payment.activecard.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.ActiveCardRequest;
import com.nawasoft.datalayer.http.apimodel.response.activecard.ActiveCardResponse;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;

public class ActiveCardModel implements ActiveCardMVP.Model {

    private IApiService service;
    private ISharedPref sharedPref;

    public ActiveCardModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<ActiveCardResponse> activeCard(String serialNumber, String password) {
        return service.activeCard(
                new ActiveCardRequest(
                        ApiTags.ActiveCard.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId()),
                        serialNumber,
                        password
                )
        );
    }
}
