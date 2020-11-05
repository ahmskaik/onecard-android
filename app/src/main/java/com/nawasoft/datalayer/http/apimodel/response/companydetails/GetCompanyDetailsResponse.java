package com.nawasoft.datalayer.http.apimodel.response.companydetails;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetCompanyDetailsResponse extends BaseResponse<GetCompanyDetailsData> {

    public GetCompanyDetailsResponse(String tag, boolean error, BaseMeta meta, BaseData<GetCompanyDetailsData> data) {
        super(tag, error, meta, data);
    }
}
