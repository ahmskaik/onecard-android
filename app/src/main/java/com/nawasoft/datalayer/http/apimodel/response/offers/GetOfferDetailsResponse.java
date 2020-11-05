package com.nawasoft.datalayer.http.apimodel.response.offers;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetOfferDetailsResponse extends BaseResponse<GetOfferDetailsData> {
    public GetOfferDetailsResponse(String tag, boolean error, BaseMeta meta, BaseData<GetOfferDetailsData> data) {
        super(tag, error, meta, data);
    }
}
