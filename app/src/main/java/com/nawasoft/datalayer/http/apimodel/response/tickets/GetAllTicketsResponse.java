package com.nawasoft.datalayer.http.apimodel.response.tickets;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetAllTicketsResponse extends BaseResponse<GetAllTicketsData> {
    public GetAllTicketsResponse(String tag, boolean error, BaseMeta meta, BaseData<GetAllTicketsData> data) {
        super(tag, error, meta, data);
    }
}
