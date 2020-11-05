package com.nawasoft.datalayer.http.apimodel.response.oreders.company;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetCompanyOrdersResponse extends BaseResponse<GetCompanyOrdersData> {

    public GetCompanyOrdersResponse(String tag, boolean error, BaseMeta meta, BaseData<GetCompanyOrdersData> data) {
        super(tag, error, meta, data);
    }
}
