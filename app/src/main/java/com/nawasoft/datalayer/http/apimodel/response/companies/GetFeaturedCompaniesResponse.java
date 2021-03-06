package com.nawasoft.datalayer.http.apimodel.response.companies;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetFeaturedCompaniesResponse extends BaseResponse<GetCompaniesResponseData> {

    public GetFeaturedCompaniesResponse(String tag, boolean error, BaseMeta meta, BaseData<GetCompaniesResponseData> data) {
        super(tag, error, meta, data);
    }
}
