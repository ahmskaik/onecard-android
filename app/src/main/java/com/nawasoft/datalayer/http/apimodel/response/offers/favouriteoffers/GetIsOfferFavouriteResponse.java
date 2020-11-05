package com.nawasoft.datalayer.http.apimodel.response.offers.favouriteoffers;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetIsOfferFavouriteResponse extends BaseResponse<GetIsOfferFavouriteData> {

    public GetIsOfferFavouriteResponse(String tag, boolean error, BaseMeta meta, BaseData<GetIsOfferFavouriteData> data) {
        super(tag, error, meta, data);
    }
}
