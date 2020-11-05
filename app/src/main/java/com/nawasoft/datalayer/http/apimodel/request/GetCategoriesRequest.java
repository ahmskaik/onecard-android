package com.nawasoft.datalayer.http.apimodel.request;

import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetCategoriesRequest extends BaseRequest {

    public GetCategoriesRequest(String tag, String language) {
        super(tag, language);
    }
}
