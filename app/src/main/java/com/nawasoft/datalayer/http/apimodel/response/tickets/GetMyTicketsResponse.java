package com.nawasoft.datalayer.http.apimodel.response.tickets;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetMyTicketsResponse extends BaseResponse<GetMyTicketsData> {

    public GetMyTicketsResponse(String tag, boolean error, BaseMeta meta, BaseData<GetMyTicketsData> data) {
        super(tag, error, meta, data);
    }
}
