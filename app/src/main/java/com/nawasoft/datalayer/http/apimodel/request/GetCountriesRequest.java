package com.nawasoft.datalayer.http.apimodel.request;

import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetCountriesRequest extends BaseRequest {
    public GetCountriesRequest(String tag, String language) {
        super(tag, language);
    }
}
