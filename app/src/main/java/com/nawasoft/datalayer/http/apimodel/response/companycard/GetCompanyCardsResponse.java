package com.nawasoft.datalayer.http.apimodel.response.companycard;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetCompanyCardsResponse extends BaseResponse<GetCompanyCardsData> {

    public GetCompanyCardsResponse(String tag, boolean error, BaseMeta meta, BaseData<GetCompanyCardsData> data) {
        super(tag, error, meta, data);
    }
}
