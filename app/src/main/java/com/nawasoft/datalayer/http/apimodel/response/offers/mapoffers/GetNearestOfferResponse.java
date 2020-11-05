package com.nawasoft.datalayer.http.apimodel.response.offers.mapoffers;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetNearestOfferResponse extends BaseResponse<GetNearestOffersData> {

    public GetNearestOfferResponse(String tag, boolean error, BaseMeta meta, BaseData<GetNearestOffersData> data) {
        super(tag, error, meta, data);
    }
}
