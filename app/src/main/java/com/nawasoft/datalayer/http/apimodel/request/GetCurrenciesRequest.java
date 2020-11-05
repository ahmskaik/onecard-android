package com.nawasoft.datalayer.http.apimodel.request;

import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetCurrenciesRequest extends BaseRequest {

    public GetCurrenciesRequest(String tag, String language) {
        super(tag, language);
    }
}
