package com.nawasoft.datalayer.http.apimodel.response.offers.favouriteoffers;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.http.apimodel.response.offers.GetOffersData;

public class GetFavouriteOffersResponse extends BaseResponse<GetOffersData> {
    public GetFavouriteOffersResponse(String tag, boolean error, BaseMeta meta, BaseData<GetOffersData> data) {
        super(tag, error, meta, data);
    }
}
