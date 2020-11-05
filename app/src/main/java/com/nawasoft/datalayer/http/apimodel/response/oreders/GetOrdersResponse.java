package com.nawasoft.datalayer.http.apimodel.response.oreders;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetOrdersResponse extends BaseResponse<GetOrdersData> {

    public GetOrdersResponse(String tag, boolean error, BaseMeta meta, BaseData<GetOrdersData> data) {
        super(tag, error, meta, data);
    }
}
