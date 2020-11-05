package com.nawasoft.datalayer.http.apimodel.response.offers;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetOffersResponse extends BaseResponse<GetOffersData> {

    public GetOffersResponse(String tag, boolean error, BaseMeta meta, BaseData<GetOffersData> data) {
        super(tag, error, meta, data);
    }
}
